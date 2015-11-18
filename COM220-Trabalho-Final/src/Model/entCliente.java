package Model;


public class entCliente {

    
    //Declaração dos atributos
     //Declaração dos atributos
    int telefone;
    String endereco;
    String nome;
    
    //construtor
    public entCliente (int pTelefone, String pEndereco, String pNome){
       setTelefone (pTelefone);
       setEndereco (pEndereco);
       setNome (pNome);
    }
    //gets e seters
    public void setTelefone (int pTelefone) {
        telefone = pTelefone;
    }
    public void setEndereco (String pEndereco) {
        endereco = pEndereco;
    }
     public void setNome (String pNome) {
        nome = pNome;
    }
     
    public int getTelefone () {
       return telefone;
    }
    public String getEndereco () {
        return endereco;
    }
     public String getNome () {
         return nome ;
    }
    
}
