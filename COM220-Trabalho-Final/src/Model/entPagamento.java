
package Model;

public class entPagamento {
    
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
            situacao = 0;
            throw new Exception("Valor pago"+pValorP+"| Troco:"+(situacao+pValorP)+"\n");
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
