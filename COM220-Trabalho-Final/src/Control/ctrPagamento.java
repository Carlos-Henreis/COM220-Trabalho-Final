
package Control;

import Model.*;
import View.*;



public class ctrPagamento {
    
    //Declaração dos atributos
    private limPagamento objALimPagamento = new limPagamento();
    private String aDadosForm;
    //private Vector vecPagamentos = new Vector();
    
    public boolean registrarPagamento(entPagamento objAEntPagamento) throws Exception {
        Registra();
        atualizaSituacao(objAEntPagamento, Double.parseDouble(aDadosForm));
        return true;
    }
    
    private void Registra() {
        aDadosForm = objALimPagamento.montaForm();
    }
    
    private void atualizaSituacao(entPagamento objAEntPagamento, double pValor) throws Exception {
        objAEntPagamento.setSitucao(pValor);
        
    }
    
    
    
}
