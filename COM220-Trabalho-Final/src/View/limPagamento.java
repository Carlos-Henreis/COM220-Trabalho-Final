
package View;

import Model.*;
import javax.swing.JOptionPane;


public class limPagamento {
    
    public String montaForm() {
        String aDadosForm;
        aDadosForm = JOptionPane.showInputDialog("Valor");
        return aDadosForm;
    }
    
}
