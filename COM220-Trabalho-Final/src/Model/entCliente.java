package Model;

import java.io.Serializable;

public class entCliente implements Serializable {

    
    //Declaração dos atributos
    String cpf;
    String telefone;
    String nome;
    String endereco[] = new String[4];//CEP, Cidade, Rua, Numero;
    
    //construtor
    public entCliente (String pTelefone,String pCEP, String pCidade, String pRua,String pNumero, String pNome, String pCpf){
       setTelefone (pTelefone);
       setEndereco (pCEP, pCidade, pRua, pNumero);
       setNome (pNome);
       setCpf (pCpf);
    }

    public entCliente() {
        
    }
    //gets e seters
    public void setTelefone (String pTelefone) {
        telefone = pTelefone;
    }
    public void setEndereco (String pCEP, String pCidade, String pRua,String pNumero) {
        endereco[0] = pCEP;
        endereco[1] = pCidade;
        endereco[2] = pRua;
        endereco[3] = pNumero;
    }
     public void setNome (String pNome) {
        nome = pNome;
    }
    
    public void setCpf (String pCpf){
        cpf = pCpf;
    }
     
    public String getTelefone () {
       return telefone;
    }
    public String[] getEndereco () {
        return endereco;
    }
     public String getNome () {
         return nome ;
    }
     
    public String getCpf (){
        return cpf;
    }
    
}
