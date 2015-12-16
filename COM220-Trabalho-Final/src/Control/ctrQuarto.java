package Control;

import Model.*;
import View.*;
import java.io.*;
import java.util.Vector;

public class ctrQuarto {
    
    //Declaração dos atributos
    private limQuarto objALimQuarto = new limQuarto();
    private entQuarto objAEntQuarto;
    private String[] aDadosForm;
    private Vector vecQuartos = new Vector();;
    private final String arquivo = "quartos_cad.dat";
    
    public ctrQuarto() throws Exception {
        desserializaQuarto();
    }
    
    public boolean cadastrarQuarto() {
        objAEntQuarto = new entQuarto();
        cadastra();
        objAEntQuarto.setNumero(Integer.parseInt(aDadosForm[0]));
        objAEntQuarto.setPreco(Double.parseDouble(aDadosForm[1]));
        objAEntQuarto.setDescricao(aDadosForm[2]);
        addVetor(objAEntQuarto);
        return true;
    }
    
    private void cadastra() {
        aDadosForm = objALimQuarto.montaForm();
    }
    
    private void salva() {
        
    }
    
    public void addVetor(entQuarto pQuarto) {
        vecQuartos.add(pQuarto);
    }
    
    
    public void RmQuarto (){
        int num;
        if (vecQuartos.size() == 0){
            objALimQuarto.montaFormVazio();
        }
        else{
            do{
                num  = Integer.parseInt(objALimQuarto.montaFormNumQuarto());
                objALimQuarto.montaFormEnc();
            }while(!Quarto_Cad(num));
            RemoveQuarto (num);
        }
    }
    
    private void RemoveQuarto (int pNumero) {
        for (int z = 0; z < vecQuartos.size(); z++) {
            if (((entQuarto)vecQuartos.elementAt(z)).getNumero() == pNumero){
                vecQuartos.removeElementAt(z);
                objALimQuarto.montaFormRmQuarto();
            }
        }
    }
    
    
    
    public void AlteraQuarto (){
        int num;
        if (vecQuartos.size() == 0){
            objALimQuarto.montaFormVazio();
        }
        else{
            do{
                num  = Integer.parseInt(objALimQuarto.montaFormNumQuarto());
                objALimQuarto.montaFormEnc();
            }while(!Quarto_Cad(num));
            AlteraQt (num);
        }
    }
    
    public void AlteraQt (int pNumero) {
        aDadosForm = objALimQuarto.montaFormNovo();
        for (int z = 0; z < vecQuartos.size(); z++) {
            if (((entQuarto)vecQuartos.elementAt(z)).getNumero() == pNumero){
                ((entQuarto)vecQuartos.elementAt(z)).setNumero(Integer.parseInt(aDadosForm[0]));
                ((entQuarto)vecQuartos.elementAt(z)).setPreco(Double.parseDouble(aDadosForm[1]));
                ((entQuarto)vecQuartos.elementAt(z)).setDescricao(aDadosForm[2]);
                objALimQuarto.montaFormAlteraQuarto();
            }
        }
    }
    
    private boolean Quarto_Cad(int num) {
        for (int i = 0; i < vecQuartos.size(); i++){
             if(num == ((entQuarto)vecQuartos.elementAt(i)).getNumero())
                 return true;
        }
        return false;
    }
    
    private void serializaQuarto() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vecQuartos);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaQuarto() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vecQuartos = (Vector) objIS.readObject();
            objIS.close();
        }
        return;
    }
    
    public Vector getListaQuartos() {
        return vecQuartos;
    }
    
    public void finalize() throws Exception {
        serializaQuarto();
    }
    
    public String imprimeQuartos() {
        String result = "";
        if (vecQuartos.size()==0)
            result+="Não existem Quartos cadastrados\n";
        else{
            result +=  "Numero \t Preco\t Descricao\n";
            for (int intIdx = 0; intIdx < vecQuartos.size();intIdx++) {
                    entQuarto objViewquarto = (entQuarto)vecQuartos.elementAt(intIdx);
                    result += objViewquarto.getNumero()+ "\t" + objViewquarto.getPreco()+ "\t" + objViewquarto.getDescricao() + "\n";
            }
        }
        return result;
    }
}

   
   
    



 
