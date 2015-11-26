package Model;


public class entCliente {

    
    //Declaração dos atributos
    int cpf;
    int telefone;
    String nome;
    String endereco[];
    
    //construtor
    public entCliente (int pTelefone, String[] pEndereco, String pNome, int pCpf){
       setTelefone (pTelefone);
       setEndereco (pEndereco);
       setNome (pNome);
       setCpf (pCpf);
    }
    //gets e seters
    public void setTelefone (int pTelefone) {
        telefone = pTelefone;
    }
    public void setEndereco (String[] pEndereco) {
        endereco = new String[4];//CEP, Cidade, Rua, Numero
        endereco = pEndereco;
    }
     public void setNome (String pNome) {
        nome = pNome;
    }
    
    public void setCpf (int pCpf){
        cpf = pCpf;
    }
     
    public int getTelefone () {
       return telefone;
    }
    public String[] getEndereco () {
        return endereco;
    }
     public String getNome () {
         return nome ;
    }
     
    public int getCpf (){
        return cpf;
    }
    
}
