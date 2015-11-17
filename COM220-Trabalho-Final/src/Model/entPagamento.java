
package Model;

public class entPagamento {
    
    //Declaração dos atributos
    double valor;
    String descricao;
    
    //construtor
    public entPagamento (double pValor, String pDescricao){
       setValor (pValor);
       setDescricao (pDescricao);
    }
    //gets e seters
    public void setValor (double pValor) {
        valor = pValor;
    }

     public void setDescricao (String pDescricao) {
         descricao = pDescricao;
    }
     
    public double getPreco () {
       return valor;
    }
     public String getDescricao () {
         return descricao ;
    }
   //outros metodos
    
}
