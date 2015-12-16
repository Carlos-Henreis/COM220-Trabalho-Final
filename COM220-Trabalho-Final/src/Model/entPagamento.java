
package Model;
import java.io.Serializable;


public class entPagamento implements Serializable{
    
    //Declaração dos atributos
    double valor;
    double situacao;
    
    //construtor
    public entPagamento (double pValor) throws Exception{
       setValor (pValor);
       setSitucao (0);
    }

    public entPagamento() {
        
    }

    //gets e seters
    public void setValor (double pValor) {
        valor = pValor;
    }

    public void setSitucao (double pValorP) throws Exception{
        if (situacao+pValorP > 0) {
            double troco = situacao+pValorP;
            situacao = 0;
            throw new Exception("Valor pago"+pValorP+"| Troco:"+troco+"\n");
        }
         situacao = situacao+pValorP;
    }
     
    public double getPreco () {
       return valor;
    }
    
    public double getSituacao () {
         return situacao ;
    }
    
}
