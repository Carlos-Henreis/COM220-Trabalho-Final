package Model;


public class entCliente {

    
    //Declaração dos atributos
    int cpf;
    int telefone;
    String nome;
    String endereco[];
    
    //construtor
    public entCliente (int pTelefone,String pCEP, String pCidade, String pRua,String pNumero, String pNome, int pCpf){
       setTelefone (pTelefone);
       setEndereco (pCEP, pCidade, pRua, pNumero);
       setNome (pNome);
       setCpf (pCpf);
    }

    public entCliente() {
        
    }
    //gets e seters
    public void setTelefone (int pTelefone) {
        telefone = pTelefone;
    }
    public void setEndereco (String pCEP, String pCidade, String pRua,String pNumero) {
        endereco = new String[4];//CEP, Cidade, Rua, Numero
        endereco[0] = pCEP;
        endereco[1] = pCidade;
        endereco[2] = pRua;
        endereco[3] = pNumero;
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
