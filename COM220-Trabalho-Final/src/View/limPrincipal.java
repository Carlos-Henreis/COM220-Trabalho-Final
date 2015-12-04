package View;

import javax.swing.*;

public class limPrincipal {

    public int montaMenu() {
        int escolha = -1;
        String escolhaInformada = "";
        do {
            try {
                escolhaInformada =
                        JOptionPane.showInputDialog(
                        "Escolha uma opção do menu:\n"
                        + "[1] Fazer Reserva \n"
                        + "[2] Adicionar cliente\n"
                        +"---------------------------\n"                                
                        + "[3] Adicionar Quarto\n"
                        + "[4] Visualizar Quarto\n"
                        + "[5] Visualizar Cliente\n"
                        + "[6] Visualizar Reservas\n"
                        + "[7] Finaliza");
                escolha = Integer.parseInt(escolhaInformada);
            } catch (Exception exc) {
            }
        } while ((escolha < 1) || (escolha > 7));
        return escolha;
    }
}


