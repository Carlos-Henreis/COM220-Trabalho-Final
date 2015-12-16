
package View;

import Model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;


public class limReserva {
    private String pCPFCliente;
    private Date aDateIn;
    private Date aDateOut;
    private double aDesconto;
    private String aNumero;
    private Vector vecQuartosSelecionadas;

    public Vector montaFormDadosQuartos (Vector vecQuartosDis) {
    	vecQuartosSelecionadas = new Vector();
    	String QuartoSel, Quarto = "";
         if (vecQuartosDis.size() == 0){
            JOptionPane.showMessageDialog(null, "Não existe quartos disponiveis para o periodo Reserva não efetuada");
            return null;
        }
        for (int i = 0; i < vecQuartosDis.size(); i++) {
            Quarto += ((entQuarto) vecQuartosDis.elementAt(i)).getNumero();
            Quarto += " ";
        }
        while (true) {
            QuartoSel = JOptionPane.showInputDialog("Digite o numeto do quarto ou S para sair. Escolha entre: " + Quarto);
            if (QuartoSel.equalsIgnoreCase("S")) {
                break;
            }
            vecQuartosSelecionadas.add(QuartoSel);
        }
        return vecQuartosSelecionadas;
    }
    
     public void montaFormaDataOut(Date Data) {
        JOptionPane.showMessageDialog(null, "Data inválida (tem que ser maior ou igual a"+Data+")");
    }
    
    public void montaFormaDataIn(Date hoje) {
        JOptionPane.showMessageDialog(null, "Data inválida (tem que ser maior ou igual a"+hoje+")");
    }
    
    public void montaFormaNum() {
         JOptionPane.showMessageDialog(null, "Numero da reserva já existe digite outro");
    }
    
    public void montaFormaNovoCliente() {
         JOptionPane.showMessageDialog(null, "Cliente não cadastrado. Iremos fazer o cadastro");
     }
     
    public void montaFormaClienteCadas() {
        JOptionPane.showMessageDialog(null, "Cliente já possui cadastrado. Iremos Proceder com a reserva");
    }
    
    public void geraFomaDesconto () {
        JOptionPane.showMessageDialog(null, "Desconto concedido para as próximas reservas");
    }
 
    public void montaFormaNovoPagamento() {
         JOptionPane.showMessageDialog(null, "O cliente deve efetuar o pagamento agora senão a reserva vai ser cancelada");
    }
    
    public Vector MontaFormperiodo () throws ParseException{
        Date hoje = new Date();
        Vector vecADadosForm = new Vector();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        do{
            aDateIn = formatter.parse(JOptionPane.showInputDialog("Digite a data de entrada"));
            if (0>(int) ((hoje.getTime() - aDateIn.getTime()) / 86400000L)){
                JOptionPane.showMessageDialog(null, "Data inválida (tem que ser menor a"+hoje+")");
            }
        }while(0>((int) ((hoje.getTime() - aDateIn.getTime()) / 86400000L)));
        do{
            aDateOut = formatter.parse(JOptionPane.showInputDialog("Digite a data de Saida"));
            if (0>(int) ((aDateIn.getTime() - aDateOut.getTime()) / 86400000L)){
                JOptionPane.showMessageDialog(null, "Data inválida (tem que ser menor a"+aDateIn+")");
            }
        }while(0>(int) ((aDateIn.getTime() - aDateOut.getTime()) / 86400000L));
        vecADadosForm.add(0 ,aDateIn);
        vecADadosForm.add(1 ,aDateOut);
        return vecADadosForm;
    }
    
   
}

