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
    private limCliente objALimCliente = new limCliente();
    private entCliente objAEntCliente;
    private String[] aDadosForm;
    private Vector vecClientes = new Vector();
    private final String arquivo = "Clientes_cad.dat";
    
    public ctrCliente() throws Exception {
        desserializaCliente();
    }
    
    public boolean cadastrarCliente() {
        objAEntCliente = new entCliente();
        cadastra();
        objAEntCliente.setCpf(Integer.parseInt(aDadosForm[0]));
        objAEntCliente.setTelefone(Integer.parseInt(aDadosForm[1]));
        objAEntCliente.setNome(aDadosForm[3]);
        objAEntCliente.setEndereco(aDadosForm[4], aDadosForm[5], aDadosForm[6], aDadosForm[7]);
        addVetor(objAEntCliente);
        return true;
    }
    
    private void cadastra() {
        aDadosForm = objALimCliente.montaForm();
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


 