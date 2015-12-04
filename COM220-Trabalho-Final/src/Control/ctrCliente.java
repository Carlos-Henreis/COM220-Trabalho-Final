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
    
    public boolean cadastrarCliente() {
        objAEntCliente = new entCliente();
        do{
            objAEntCliente.setCpf(objALimCliente.montaFormDadosCPF());
            if (!CPF_Val(objAEntCliente.getCpf())){
                objALimCliente.montaFormaCpfInvalido();
            }   
        }while (!CPF_Val(objAEntCliente.getCpf()));
        aDadosForm = objALimCliente.montaForm();
        objAEntCliente.setTelefone(aDadosForm[0]);
        objAEntCliente.setNome(aDadosForm[1]);
        objAEntCliente.setEndereco(aDadosForm[2], aDadosForm[3], aDadosForm[4], aDadosForm[5]);
        addVetor(objAEntCliente);
        return true;
    }
    
    private boolean CPF_Val(String pCpfClinte) {
        Vector Clientes = new Vector();
        Clientes =  objCtrPrincipal.getObjCtrCliente().getListaClientes();
        for (int i = 0; i < Clientes.size(); i++){
             if(pCpfClinte.equals(((entCliente)Clientes.elementAt(i)).getCpf()))
                 return false;
        }
        return true;
    }
    private void salva() {
        
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
            result+="Não existem clinetes cadastrados\n";
        else{
            result +=  "Cpf \t Nome \t Telefone\n";
            for (int intIdx = 0; intIdx < vecClientes.size();intIdx++) {
                    entCliente objViewDis = (entCliente)vecClientes.elementAt(intIdx);
                    result += objViewDis.getCpf() + "\t" + objViewDis.getNome() + "\t" + objViewDis.getTelefone() + "\n";
            }
        }
        return result;
    }
}


 