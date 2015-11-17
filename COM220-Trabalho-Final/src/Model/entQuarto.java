
package Model;


public class entQuarto {
     //Declaração dos atributos
    double preco;
    int numero;
    String descricao;
    
    //construtor
    public entQuarto (double pPreco, int pNumero, String pDescricao){
       setPreco (pPreco);
       setNumero (pNumero);
       setDescricao (pDescricao);
    }
    //gets e seters
    public void setPreco (double pPreco) {
        preco = pPreco;
    }
    public void setNumero (int pNumero) {
        numero = pNumero;
    }
     public void setDescricao (String pDescricao) {
         descricao = pDescricao;
    }
     
    public double getPreco () {
       return preco;
    }
    public int getNumero () {
        return numero;
    }
     public String getDescricao () {
         return descricao ;
    }
   //outros metodos
}
