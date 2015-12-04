
package View;

import javax.swing.JOptionPane;

public class limQuarto {
    public String[] montaForm() {
        String aDadosForm[] = new String[3];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite Seu Numero");
        aDadosForm[1] = JOptionPane.showInputDialog("Preco");
        aDadosForm[2] = JOptionPane.showInputDialog("Descrição");
        return aDadosForm;
    }
    
}
