
package View;

import javax.swing.JOptionPane;


/**
 *
 * @author carlos
 */
public class limCliente {
    
    String pCPFCliente;
    
     public String[] montaForm() {
        String aDadosForm[] = new String[6];
        aDadosForm[0] = JOptionPane.showInputDialog("Telefone");
        aDadosForm[1] = JOptionPane.showInputDialog("Nome");
        aDadosForm[2] = JOptionPane.showInputDialog("CEP");
        aDadosForm[3] = JOptionPane.showInputDialog("CIdade");
        aDadosForm[4] = JOptionPane.showInputDialog("Rua");
        aDadosForm[5] = JOptionPane.showInputDialog("Número");
        return aDadosForm;
    }
     
     public String montaFormDadosCPF(){
        pCPFCliente = JOptionPane.showInputDialog("Digite o CPF do clinte");
        return pCPFCliente;
    }
     
     public void montaFormaCpfInvalido() {
         JOptionPane.showMessageDialog(null, "CPF já cadastrado informe outro");
     }
    
}
