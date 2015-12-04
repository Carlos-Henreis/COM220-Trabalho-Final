package Control;

import View.limPrincipal;
import javax.swing.JOptionPane;

import View.*;
import Model.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ctrPrincipal {

    private int intAOperacao = -1;
    private ctrQuarto objACtrQuarto;
    private limPrincipal objALimPrincipal;
    private ctrCliente objACtrCliente;
    private ctrReserva objACtrReserva;

    public ctrPrincipal() {
        objALimPrincipal = new limPrincipal();
        try {
            objACtrQuarto = new ctrQuarto();
            objACtrCliente = new ctrCliente(this);
            objACtrReserva = new ctrReserva(this);
        } catch (Exception e) {
            System.out.println("Erro na abertura de arquivo");
        }

    }

    public ctrCliente getObjCtrCliente() {
        return objACtrCliente;
    }

    public ctrQuarto getObjCtrQuarto() {
        return objACtrQuarto;
    }

    public void run() {
        while (true) {
            intAOperacao = objALimPrincipal.montaMenu();
            CancelReservas();
            switch (intAOperacao) {
                case 1:
                    cadReserva();          
                    break;
                case 2:
                    cadCliente();	
                    break;
                case 3:
                    cadQuarto();
                    break;
                case 4:
                    visQuarto();
                    break;
                case 5:
                    visCliente();
                    break;
                case 6:
                    visReserva();
                    break;
                case 7:
                    finalize();
            }
        }
    }

    private boolean cadQuarto() {
        return objACtrQuarto.cadastrarQuarto();
    }

    private boolean cadCliente() {
        return objACtrCliente.cadastrarCliente();
    }
    
    private void CancelReservas(){
        objACtrReserva.CancelarReservas();
    }

    private boolean cadReserva() {
        try {
            return objACtrReserva.FazerReserva();
        } catch (ParseException ex) {
            Logger.getLogger(ctrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void visCliente() {
        String saidaStr = objACtrCliente.imprimeClientes();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void visQuarto() {
        String saidaStr = objACtrQuarto.imprimeQuartos();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista das Quartos", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void visReserva() {
        String saidaStr = objACtrReserva.imprimeReservas();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null,saidaArea , "Lista dos Reservas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void finalize() {
        try {
            objACtrCliente.finalize();
            objACtrQuarto.finalize();
            objACtrReserva.finalize();
        } catch (Exception e) {
            System.err.println("Erro ao fechar arquivo!");
        }
        System.exit(0);
    }
}
