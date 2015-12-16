
package Control;

import Model.*;
import View.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ctrPagamento {
    
    
    //Declaração dos atributos
    private limPagamento objLimPagamento = new limPagamento();
    private ctrPrincipal objCtrPrincipal;
    private double valorPagamento;
    public entPagamento objAEntPagamento = new entPagamento();
    private String aDadosForm;
    
    public ctrPagamento (ctrPrincipal pCtrPrincipal) {
        objCtrPrincipal = pCtrPrincipal;
    }

    ctrPagamento() {
        
    }
    
    public boolean registrarPagamento(String Cpf) {
        if(!Registra(Cpf)){
            objLimPagamento.montaFormPgCancel();
            return false;
        }
        else{
            return true;
        }
    }
    
    
    private boolean Registra(String cpfCliente) {
        Vector reservasCliente = new Vector();
        Vector reserva = new Vector();
        reserva=  objCtrPrincipal.getObjCtrReserva().getListaReservas();
        for (int i = 0; i < reserva.size(); i++){
            if (cpfCliente.equals(((entReserva)reserva.elementAt(i)).getCpFCliente()))
                reservasCliente.add(reserva.elementAt(i));
        }
        aDadosForm = objLimPagamento.montaFormReserva(reservasCliente);
        if (aDadosForm.equals("") ||aDadosForm.equals("S")){
            return false;
        }
        else{
            boolean teste = false;
            for (int i = 0; i < reserva.size(); i++){
                if (aDadosForm.equals(((entReserva)reserva.elementAt(i)).getNumero())){
                    objAEntPagamento = ((entReserva)reserva.elementAt(i)).getPagamento();
                    valorPagamento = Double.parseDouble(objLimPagamento.montaFormValor());
                    atualizaSituacao(valorPagamento);
                    ((entReserva)reserva.elementAt(i)).setPagamento(objAEntPagamento);
                     teste = true;
                }
            }
            if (!teste)
                return false;
            else
                return true;
        }
    }
    
    private void atualizaSituacao(double pValor) {
        try{
            objAEntPagamento.setSitucao(pValor);
        } catch (Exception ex) {
            objLimPagamento.montaFormSituacao(ex.getMessage());
        }
        
    }
    
    public double calculaValor (entReserva objEntReserva, double desconto){
        double valorReserva = 0;
        Vector quartos = new Vector();
        Vector quartos_R = new Vector();
        int dias;
        dias = (int) ((objEntReserva.getDataOut().getTime() - objEntReserva.getDatain().getTime()) / 86400000L) + 1;
        quartos = objCtrPrincipal.getObjCtrQuarto().getListaQuartos();
        quartos_R = objEntReserva.getQuartos();
        for (int i = 0; i < quartos.size(); i++)
            for (int j = 0; j < quartos_R.size();j++){
                if (Integer.parseInt((String)quartos_R.elementAt(j)) == ((entQuarto)quartos.elementAt(i)).getNumero()){
                    valorReserva += dias*((entQuarto)quartos.elementAt(i)).getPreco();
                }
            } 
        valorReserva -= valorReserva*(desconto/10);
        return valorReserva;
    }
    
    
    
}
