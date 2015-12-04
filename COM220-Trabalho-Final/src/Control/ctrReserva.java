
package Control;

import Model.*;
import View.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;


public class ctrReserva {
    //Declaração dos atributos
    private limReserva objLimReserva = new limReserva();
    private entReserva objEntReserva;
    private ctrPrincipal objCtrPrincipal;
    private Vector vecADadosForm;
    private ctrPagamento pagamento = new ctrPagamento();
    private Vector vecReserva = new Vector();
    private Vector vecReservaCancl = new Vector();
    private Vector vecQuartosDisp;
    private final String arquivo = "Reservas_cad.dat";
    
    public ctrReserva(ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        desserializaReserva();;
    }
    
    public boolean FazerReserva() throws ParseException {
        objEntReserva= new entReserva();
        objEntReserva.setCpFCliente(objLimReserva.montaFormDadosCPF());
        if (!Cliente_Cad(objEntReserva.getCpFCliente())){
            objLimReserva.montaFormaNovoCliente();
            objCtrPrincipal.getObjCtrCliente().cadastrarCliente();
        }
        else{
            objLimReserva.montaFormaClienteCadas();
        }
        vecADadosForm = objLimReserva.montaForm(objEntReserva.getCpFCliente());
        objEntReserva.setDatain((Date) vecADadosForm.elementAt(1));
        objEntReserva.setDataOut((Date) vecADadosForm.elementAt(2));
        objEntReserva.setDesconto((double) vecADadosForm.elementAt(3));
        VerificaQuartosDisp(objEntReserva.getDatain(), objEntReserva.getDataOut());
        if (objLimReserva.montaFormDadosQuartos(vecQuartosDisp)){
            objEntReserva.setQuartos((Vector) vecADadosForm.elementAt(4));
            addVetor(objEntReserva);
        }
        return true;
    }
    
    private boolean Cliente_Cad(String pCpfClinte) {
        Vector Clientes = new Vector();
        Clientes =  objCtrPrincipal.getObjCtrCliente().getListaClientes();
        for (int i = 0; i < Clientes.size(); i++){
             if(pCpfClinte.equals(((entCliente)Clientes.elementAt(i)).getCpf()))
                 return true;
        }
        return false;
    }
    
    private void VerificaQuartosDisp(Date pDataIn, Date pDataOut) {
        vecQuartosDisp = new Vector();
        Vector Quartos = new Vector();
        Vector QuartosR = new Vector();
        Vector aux = new Vector ();
        
        
        
        //Encontrar toda os quartos disponíveis sem reserva no
        for (int l = 0; l < vecReserva.size(); l++){
                Quartos = ((entReserva)vecReserva.elementAt(l)).getQuartos();
                for (int z = 0; z < Quartos.size(); z++){
                    QuartosR.add(Quartos.elementAt(z));
                }
        }        
        //Lista todos os quartos que com ou sem reserva
        Quartos = objCtrPrincipal.getObjCtrQuarto().getListaQuartos();
        for (int k = 0; k <Quartos.size(); k++){

            boolean reservado = false;
            for (int z = 0; z < QuartosR.size(); z++){
               if (((entQuarto)Quartos.elementAt(k)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(z)))
                    reservado = true;
            }
            if (!reservado){
                vecQuartosDisp.add(Quartos.elementAt(k));
            }
        }
        
        //Lista de todos os quartos reservados de todas as reservas
        for (int z = 0; z < vecReserva.size(); z++) {
            Date a= new Date ();
            Date b = new Date();
            a = ((entReserva)vecReserva.elementAt(z)).getDatain();
            b = ((entReserva)vecReserva.elementAt(z)).getDataOut();
            QuartosR = ((entReserva)vecReserva.elementAt(z)).getQuartos();
            //Os quartos dessas reservasnão podem ser reservados na nova reserva
            if ((a.after(pDataIn) && a.before(pDataOut))
               || (b.after(pDataIn) && b.before(pDataOut))
               || (a.before(pDataIn) && b.after(pDataOut))
               || (a.equals(pDataIn) || b.equals(pDataIn)
               || (a.equals(pDataIn) || b.equals(pDataOut)))) {
                for (int i = 0; i < QuartosR.size(); i++)
                    for (int l = 0; l < Quartos.size(); l++)
                        if (((entQuarto)Quartos.elementAt(l)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                            aux.add(Quartos.elementAt(l));

            } else {
                for (int i = 0; i < QuartosR.size(); i++){
                    boolean quarto_r = false;
                    for (int l = 0; l < vecQuartosDisp.size(); l++)
                        if (((entQuarto)vecQuartosDisp.elementAt(l)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                            quarto_r = true;
                    if (!quarto_r)
                        for (int k = 0; k <Quartos.size(); k++)
                            if (((entQuarto)Quartos.elementAt(k)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i))){
                                boolean m = true;
                                for (int c = 0; c < aux.size(); c++) {
                                    if (((entQuarto)aux.elementAt(c)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                                        m = false;
                                }
                                if (m)
                                    vecQuartosDisp.add(Quartos.elementAt(k));
                            }
                               
              
                }
            }
                
        }
       
            
    }
    
    private void salva() {
  
    }
     
    public void CancelarReservas() {
         Date hoje = new Date ();
         for (int z = 0; z < vecReserva.size(); z++) {
            if ((3<=(int) ((hoje.getTime() - ((entReserva)vecReserva.elementAt(z)).getDatain().getTime()) / 86400000L))){
                 if (false/*!situaçãoPagamento(passar parametros)*/){
                     vecReservaCancl.add(vecReserva.elementAt(z));
                     vecReserva.remove(z);
                 }
            }
         }
         
    }
     
    public void addVetor(entReserva pReserva) {
        vecReserva.add(pReserva);
    }
    
    
    
    private void serializaReserva() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vecReserva);
        objOS.flush();
        objOS.close();
    }
    
    private void desserializaReserva() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vecReserva = (Vector) objIS.readObject();
            objIS.close();
        }
    }
    
    public Vector getListaReservas() {
        return vecReserva;
    }
    
    public void finalize() throws Exception {
        serializaReserva();
    }
    
    
    public String imprimeReservas() {
        Vector Quartos = new Vector();
        String result = "";
        if (vecReserva.size()==0)
            result+="Não existem Reservas cadastrados\n";
        else{
            for (int i = 0; i < vecReserva.size(); i++) {
                result += "Reserva"+"\nFeita por: "+ ((entReserva) vecReserva.elementAt(i)).getCpFCliente() +"\nData de entrada: " + ((entReserva) vecReserva.elementAt(i)).getDatain() + "\nData de Saída: " + ((entReserva) vecReserva.elementAt(i)).getDataOut() + "\nDisciplinas\n";
                Quartos = ((entReserva) vecReserva.elementAt(i)).getQuartos();
                for (int z = 0; z < Quartos.size(); z++) {
                    result += (String)Quartos.elementAt(z)+"\n";
		}
                result += "\n---------------------\n";
            }
        }
		return result;
    }
    
}