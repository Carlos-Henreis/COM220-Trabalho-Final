
package View;

import Model.*;
import java.util.Vector;
import javax.swing.JOptionPane;


public class limPagamento {
    
    Vector vecReservasSelecionadas;

    public String montaFormValor() {
        String aDadosForm;
        aDadosForm = JOptionPane.showInputDialog("Valor");
        return aDadosForm;
    }
    
    public String MontaFormCliente () {
        String cpf;
        cpf = JOptionPane.showInputDialog("Digite o CPF do cliente");
        return cpf;
    }
    
    public String montaFormReserva(Vector reservasCliente) {
        vecReservasSelecionadas = new Vector();
    	String ReservaSel = "", Reserva = "";
         if (reservasCliente.size() == 0){
            JOptionPane.showMessageDialog(null, "Não existem reservas a serem pagas por esse cliente");
            return "";
        }
        for (int i = 0; i < reservasCliente.size(); i++) {
            Reserva += ((entReserva) reservasCliente.elementAt(i)).getNumero();
            Reserva += " ";
        }
        boolean loop_fim = false;
        while (!loop_fim) {
            ReservaSel = JOptionPane.showInputDialog("Digite o numero da reserva ou S para sair. Escolha entre: " + Reserva);
            for (int i = 0; i < reservasCliente.size(); i++) {
               if (ReservaSel.equals(((entReserva)reservasCliente.elementAt(i)).getNumero())){
                    loop_fim = true;
               }
            }
            if (ReservaSel.equalsIgnoreCase("S")) {
                loop_fim = true;
            }
        }
        return ReservaSel;
    }
    
    public void montaFormPgCancel(){
        JOptionPane.showMessageDialog(null, "Cliente não selecionou nenhuma reserva para efetuar o pagamento");
    }
    
    public void montaFormSituacao(String situacao){
        JOptionPane.showMessageDialog(null, situacao);
    }
}