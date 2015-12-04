/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;


public class limReserva {
    private String pCPFCliente;
    private Date aDateIn;
    private Date aDateOut;
    private double aDesconto;
    private Vector vecQuartosSelecionadas;
    private Vector vecADadosForm = new Vector();

    
    public Vector montaForm(String pCPFCliente) throws ParseException {
        montaFormDadosReserva();
        vecADadosForm.add(0, pCPFCliente);
        vecADadosForm.add(1 ,aDateIn);
        vecADadosForm.add(2 ,aDateOut);
        vecADadosForm.add(3 ,aDesconto);
        return vecADadosForm;
    }
    
    public String montaFormDadosCPF(){
        pCPFCliente = JOptionPane.showInputDialog("Digite o CPF do clinte");
        return pCPFCliente;
    }
    
    
    private void montaFormDadosReserva() throws ParseException {
        Date hoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        do{
            aDateIn = formatter.parse(JOptionPane.showInputDialog("Digite a data de entrada"));
            if (0<(int) ((hoje.getTime() - aDateIn.getTime()) / 86400000L)){
                JOptionPane.showMessageDialog(null, "Data inválida (tem que ser maior ou igual a"+hoje+")");
            }
        }while(0<(int) ((hoje.getTime() - aDateIn.getTime()) / 86400000L));
        do{
            aDateOut = formatter.parse(JOptionPane.showInputDialog("Digite a data de Saida"));
            if (0<(int) ((aDateIn.getTime() - aDateOut.getTime()) / 86400000L)){
                JOptionPane.showMessageDialog(null, "Data inválida (tem que ser maior ou igual a"+aDateIn+")");
            }
        }while(0<(int) ((aDateIn.getTime() - aDateOut.getTime()) / 86400000L));
        aDesconto = Double.parseDouble(JOptionPane.showInputDialog("Digite o deconto"));//O Gerente que irá definir
    }
    
    
    public boolean montaFormDadosQuartos (Vector vecQuartosDis) {
    	vecQuartosSelecionadas = new Vector();
    	String QuartoSel, Quarto = "";
         if (vecQuartosDis.size() == 0){
            JOptionPane.showMessageDialog(null, "Não existe quartos disponiveis para o periodo Reserva não efetuada");
            return false;
        }
        for (int i = 0; i < vecQuartosDis.size(); i++) {
            Quarto += ((entQuarto) vecQuartosDis.elementAt(i)).getNumero();
            Quarto += " ";
        }
        while (true) {
            QuartoSel = JOptionPane.showInputDialog("Digite o numeto do quarto ou S para sair. Escolha entre: " + Quarto);
            if (QuartoSel.equalsIgnoreCase("S")) {
                break;
            }
            vecQuartosSelecionadas.add(QuartoSel);
        }
        vecADadosForm.add(4, vecQuartosSelecionadas);
        return true;
    }
    
     public void montaFormaNovoCliente() {
         JOptionPane.showMessageDialog(null, "Cliente não cadastrado. Iremos fazer o cadastro");
     }
     
    public void montaFormaClienteCadas() {
        JOptionPane.showMessageDialog(null, "Cliente já possui cadastrado. Iremos Proceder com a reserva");
    }
    
}

