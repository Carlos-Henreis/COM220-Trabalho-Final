package View;

import Control.ctrPrincipal;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class limPrincipal extends JFrame{
    private ctrPrincipal pObjPrincipal;
    private JPanel cards;
    private final String Cliente_PANEL = "Painel destinado ao gerente";
    private final String Gerente_PANEL = "Painel destinado ao cliente";
    private final String Sobre_PANEL = "Painel com infoormações sobre o sistema";
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private JMenuBar menuBar;
    private JMenu menu;
    public JTextField senha;
    public JButton btn = new JButton("verificar");
   
    public limPrincipal(ctrPrincipal ObjPrincipal){
        super("Sistema de Gerenciamento de Reservas");
        pObjPrincipal = ObjPrincipal;
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        
        menuItem1 = new JMenuItem("Área do Cliente");

        menuItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               CardLayout layout = (CardLayout) cards.getLayout();
               layout.show(cards, Cliente_PANEL);
               pObjPrincipal.CancelReservas();
            }
        });

        menuItem2 = new JMenuItem("Área do gerente");
        
        menuItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                CardLayout layout = (CardLayout) cards.getLayout();
                layout.show(cards, Gerente_PANEL);
                pObjPrincipal.CancelReservas(); 
            }
        });
        
        menuItem3 = new JMenuItem("Sobre");
        
        menuItem3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                CardLayout layout = (CardLayout) cards.getLayout();
                layout.show(cards, Sobre_PANEL);
                pObjPrincipal.CancelReservas();
            }
        });
        
        menuItem4 = new JMenuItem("Sair");
        
        menuItem4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                 JOptionPane.showMessageDialog(null, "Arquivos Persistidos programas Será Fechado", "Lista Clientes", JOptionPane.INFORMATION_MESSAGE);  
                pObjPrincipal.finalize();
                   
            }
        });
        

        menuBar = new JMenuBar();   
        menu = new JMenu("Menu Principal");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        //Painel de boas vindas
        JPanel p1 = new JPanel();
        p1.add(new JLabel("<html>"+MontaFormInicio()+"</html>"));
        p1.setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        // Cria o segundo painel Funções relacionadas ao cliente
        JPanel p2 = pObjPrincipal.FormlimCliente();
        // Cria o terceiro painel Funções relacionadas ao Gerente
        JPanel p3 = pObjPrincipal.FormlimGerente();;
        
        
        
        
        
        
        //Cria o quarto painel Sobre
        JPanel p4 = new JPanel();
        p4.add(new JLabel("<html>"+MontaFormInicio()+"</html>"));
        p4.add(new JLabel("<html>"+MontaFormSobre()+"</html>"));
        // Inicializa o container e seta o layout
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        cards.add("", p1);
        cards.add(Cliente_PANEL, p2);
        cards.add(Gerente_PANEL, p3);
        cards.add(Sobre_PANEL, p4);
        cards.setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        this.add(cards);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public String MontaFormInicio(){
        String str = "";
        str += "<div style=\"background-color: #D9D9F3; border-top: 1px solid #545454; border-bottom: 1px solid #545454; padding: 4px; text-align: center;\"> <h1 align=\"center\"><font face=\"FORTE\"> Bem vindo ao Sistema de Gerenciamento de Reservas</font></h1></div>";
        return str;
    }   
    
    public String MontaFormSobre(){
        String str = "";
        
        str += "<div style=\"background-color: #D9D9F3; border-top: 1px solid #545454; border-bottom: 1px solid #545454; padding: 4px; text-align: center;\"> <h4 align=\"center\"><font face=\"FORTE\"> Sistema de Gerenciamento de Reservas<br> "                                                                                                                                                                                      + " Universidade Federal de Itajuba - UNIFEI - Bacharelado em Ciencia da Computacao - 2015 - 2 Semestre\n"                                                                                                                                                   +"</font></h1></div>";
        return str;
    }  
    
    public int montaMenuRel() {
        int escolha = -1;
        String escolhaInformada = "";
        do {
            try {
                escolhaInformada =
                        JOptionPane.showInputDialog(
                        "Escolha uma opção do menu:\n"
                        + "[1] Gerar relatório Reserva Canceladas hoje\n"
                        + "[2] Gerar relatório Reserva efetivadas hoje\n"
                        + "[3] Gerar relatório Reserva não pagas hoje\n"
                        +"---------------------------\n"                                
                        + "[4] Gerar relatório de um periodo anterior\n"
                        + "[5] Sair\n");
                escolha = Integer.parseInt(escolhaInformada);
            } catch (Exception exc) {
            }
        } while ((escolha < 1) || (escolha > 5));
        return escolha;
    }


}


