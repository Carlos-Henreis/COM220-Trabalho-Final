/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.*;
import View.*;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author carlos
 */
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

   
   
    



 
