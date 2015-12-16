package Control;

import Model.*;
import View.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ctrCliente {
    
    //Declaração dos atributos
    private ctrPrincipal objCtrPrincipal;
    private limCliente objALimCliente = new limCliente();
    private entCliente objAEntCliente;
    private String[] aDadosForm;
    private Vector vecClientes = new Vector();
    private final String arquivo = "Clientes_cad.dat";
    
    public ctrCliente(ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        desserializaCliente();
    }
    
    public boolean cadastrarCliente(String pCpf, String pNome,  String pTelefone, String pCep, String pCidade, String pRua, String pNumero) {
        objAEntCliente = new entCliente();
        objAEntCliente.setCpf(pCpf);
        if (!CPF_Val(objAEntCliente.getCpf())){
            objALimCliente.montaFormaCpfInvalido();
            return true;
        }   
        objAEntCliente.setTelefone(pTelefone);
        objAEntCliente.setNome(pNome);
        objAEntCliente.setEndereco (pCep, pCidade, pRua, pNumero);
        addVetor(objAEntCliente);
        objALimCliente.montaFormaClienteAdd();
        return true;
    }
    
    public boolean CPF_Val(String pCpfClinte) {
        Vector Clientes = new Vector();
        Clientes =  objCtrPrincipal.getObjCtrCliente().getListaClientes();
        for (int i = 0; i < Clientes.size(); i++){
             if(pCpfClinte.equals(((entCliente)Clientes.elementAt(i)).getCpf()))
                 return false;
        }
        return true;
    }
    
    public void addVetor(entCliente pCliente) {
        vecClientes.add(pCliente);
    }

    private void serializaCliente() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vecClientes);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaCliente() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vecClientes = (Vector) objIS.readObject();
            objIS.close();
        }
    }
    
    public Vector getListaClientes() {
        return vecClientes;
    }
    
    public void finalize() throws Exception {
        serializaCliente();
    }
    
    
   
    public String imprimeClientes() {
        String result = "";
        if (vecClientes.size()==0)
           result+="Não existem Clientes cadastrados<br>";
        else{
            result +=  "<CENTER><FONT COLOR=BLUE SIZE=6>Todos os clientes cadastrados</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Cpf </TD> "
                    + "<TD>Nome </TD>"
                    + "<TD>Telefone </TD>"
                    + " </TR>";
            for (int intIdx = 0; intIdx < vecClientes.size();intIdx++) {
                entCliente objViewDis = (entCliente)vecClientes.elementAt(intIdx);
                result +=  "<TR>"+"<TD>"+objViewDis.getCpf()+ "</TD><TD>" +  objViewDis.getNome()+ "</TD><TD>" + objViewDis.getTelefone() +"</TD></TR>";
            }
            result += "</TABLE>";
        }
        return result;
    }
}


 