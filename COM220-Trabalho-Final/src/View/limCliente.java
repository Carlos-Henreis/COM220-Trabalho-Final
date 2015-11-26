
package View;

import javax.swing.JOptionPane;


/**
 *
 * @author carlos
 */
public class limCliente {
    
     public String[] montaForm() {
        String aDadosForm[] = new String[7];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite Seu CPF");
        aDadosForm[1] = JOptionPane.showInputDialog("Telefone");
        aDadosForm[2] = JOptionPane.showInputDialog("Nome");
        aDadosForm[3] = JOptionPane.showInputDialog("CEP");
        aDadosForm[4] = JOptionPane.showInputDialog("CIdade");
        aDadosForm[5] = JOptionPane.showInputDialog("Rua");
        aDadosForm[6] = JOptionPane.showInputDialog("Número");
        return aDadosForm;
    }
    
}
