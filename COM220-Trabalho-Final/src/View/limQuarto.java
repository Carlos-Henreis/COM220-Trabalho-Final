
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
    
    public String montaFormNumQuarto() {
        return JOptionPane.showInputDialog("Digite Seu Numero");
    }
    
    public void montaFormRmQuarto () {
        JOptionPane.showMessageDialog(null, "O quarto foi removido");
    }
    
    public void  montaFormEnc () {
        JOptionPane.showMessageDialog(null, "Número de quarto não existe tente outro");
    }
    
   
    
    public void montaFormAlteraQuarto () {
        JOptionPane.showMessageDialog(null, "Dados do quarto foram alterados com sucesso!");
    }
    
    public void montaFormVazio () {
        JOptionPane.showMessageDialog(null, "Impossível proceguir pois não tem nenhum quarto cadastrado");
    }
    
    public String[] montaFormNovo() {
        String aDadosForm[] = new String[3];
        aDadosForm[0] = JOptionPane.showInputDialog("Digite o noSeuov Numero");
        aDadosForm[1] = JOptionPane.showInputDialog("Entre com o novo preço");
        aDadosForm[2] = JOptionPane.showInputDialog("Mude a descrição");
        return aDadosForm;
    }
}
