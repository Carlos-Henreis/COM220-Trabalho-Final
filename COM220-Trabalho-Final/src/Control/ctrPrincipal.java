package Control;

import View.limPrincipal;
import Model.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ctrPrincipal {

    private Date hoje;
    private int intAOperacao = -1;
    private ctrQuarto objACtrQuarto;
    private limPrincipal objALimPrincipal;
    private ctrCliente objACtrCliente;
    private ctrReserva objACtrReserva;
    private ctrPagamento objACtrPagamento;

    public ctrPrincipal() {
        objACtrPagamento = new ctrPagamento(this);
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
    
    public ctrReserva getObjCtrReserva() {
        return objACtrReserva;
    }
    
    public ctrPagamento getObjCtrPagamento() {
        return objACtrPagamento;
    }

    public void run() throws Exception {
        objALimPrincipal = new limPrincipal(this);
    }
    
    private boolean FazerPagamento (String Cpf) {
        try{
            return objACtrPagamento.registrarPagamento(Cpf);
        }catch(Exception e){
        }
        return false;
    }

    private boolean cadQuarto() {
        return objACtrQuarto.cadastrarQuarto();
    }

    private boolean cadCliente(String Cpf, String Nome,  String Telefone, String Cep, String Cidade, String Rua, String Numero) {
        return objACtrCliente.cadastrarCliente(Cpf, Nome, Telefone, Cep, Cidade, Rua, Numero);
    }
    
    public void CancelReservas(){
        objACtrReserva.CancelarReservas();
    }
    
    private void ReservasEfetivadas(){
        objACtrReserva.ReservaEfetivadas();
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
    
    private JList staticList;
    private Vector vecQuartosSelecionadas = new Vector();
    private GregorianCalendar data =new GregorianCalendar();
    private final String Registrar_PANEL = "Registrar cliente";
    private final String VisQuartos_PANEL = "Visualizar quartos";
    private final String Pagar_PANEL = "Efetuar pagamento";
    private final String Reserva_PANEL = "Fazer Reserva";
    private JPanel cards, cards1;
    JTabbedPane tabbedPane = new JTabbedPane();
    JLabel labelCpf, jLabelNome, jLabelTelefone, jLabelCep, jLabelCidade, jLabelRua, jLabelNumero, labelCpf1, jLabelDataIn, JLabelDataOut,  labelCpf2, labelCpf3;
    JTextField txtCpf, txtNome, txtTelefone, txtCep, txtCidade, txtRua, txtNumero, txtcpf1, txtDataIn, txtDataOut,txtcpf2, txtcpf3, txtNome11, txtTelefone1, txtCep1, txtCidade1, txtRua1, txtNumero1;  
    
    public JPanel FormlimCliente() {
        JPanel JanelaCliente = new JPanel();
        JanelaCliente.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        labelCpf = new JLabel();
        jLabelNome = new JLabel();
        jLabelTelefone = new JLabel();
        jLabelCep = new JLabel();
        jLabelCidade = new JLabel();
        jLabelRua = new JLabel();
        jLabelNumero = new JLabel();
        
        labelCpf.setText("Cpf");
        jLabelNome.setText("Nome");
        jLabelTelefone.setText("Telefone");
        jLabelCep.setText("CEP");      
        jLabelCidade.setText("Cidade");
        jLabelRua.setText("Rua");
        jLabelNumero.setText("Numero");
        
        labelCpf.setBounds(10, 10, 240, 15);
        jLabelNome.setBounds(10, 40, 240, 15);
        jLabelTelefone.setBounds(10, 80, 240, 15);
        jLabelCep.setBounds(10, 120, 240, 15);
        jLabelCidade.setBounds(10, 160, 240, 15);
        jLabelRua.setBounds(10, 200, 240, 15);
        jLabelNumero.setBounds(10, 240, 240, 15);
        
        txtCpf = new JTextField(20);
        txtNome = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtCep = new JTextField(20);
        txtCidade = new JTextField(20);
        txtRua = new JTextField(20);
        txtNumero = new JTextField(20);
        
        txtCpf.setBounds(75, 10, 240, 20);
        txtNome.setBounds(75, 40, 240, 20);
        txtTelefone.setBounds(75, 80, 240, 20);
        txtCep.setBounds(75, 120, 240, 20);
        txtCidade.setBounds(75, 160, 240, 20);
        txtRua.setBounds(75, 200, 240, 20);
        txtNumero.setBounds(75, 240, 240, 20);
        JButton b1 = new JButton("Cadastrar");
        b1.setBounds(75, 280, 120, 40);
        p1.add(labelCpf);
        p1.add(txtCpf);
        p1.add(jLabelNome);
        p1.add(txtNome);
        p1.add(jLabelTelefone);
        p1.add(txtTelefone);
        p1.add(jLabelCep);
        p1.add(txtCep);
        p1.add(jLabelCidade);
        p1.add(txtCidade);
        p1.add(jLabelRua);
        p1.add(txtRua);
        p1.add(jLabelNumero);
        p1.add(txtNumero);
        p1.add(b1);
        
        b1.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cadCliente(txtCpf.getText(), txtNome.getText(), txtTelefone.getText(), txtCep.getText(), txtCidade.getText(), txtRua.getText(), txtNumero.getText());
                }
            }
        );

        
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        labelCpf1 = new JLabel("Digite o CPF para Proceguir");
        labelCpf1.setBounds(200, 10, 240, 15);
        txtcpf1 = new JTextField(20);
        txtcpf1.setBounds(400, 10, 240, 20);
        JButton b2 = new JButton("Verificar");
        b2.setBounds(680, 10, 240, 25);
        p2.add(labelCpf1);
        p2.add(txtcpf1);
        p2.add(b2);
        
        
       
        // Cria o segundo painel 
        JPanel p20 = new JPanel();
        
        JPanel p21 = new JPanel();
        p21.setLayout(null);
        jLabelNumero = new JLabel();
        jLabelDataIn = new JLabel();
        JLabelDataOut = new JLabel();
        
       
        jLabelNumero.setText("Numero");
        jLabelDataIn.setText("Data de Entrada");
        JLabelDataOut.setText("Data de saida");
        
        jLabelNumero.setBounds(10, 10, 240, 15);
        jLabelDataIn.setBounds(10, 40, 240, 15);
        JLabelDataOut.setBounds(10, 80, 240, 15);
        
        txtNumero = new JTextField(20);
        txtDataIn = new JTextField(20);  
        txtDataOut = new JTextField(20); 
       
        
        txtNumero.setBounds(75, 10, 240, 20);
        txtDataIn.setBounds(140, 40, 175, 20);
        txtDataOut.setBounds(140, 80, 175, 20);
        
        txtNumero.addActionListener(
            new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!objACtrReserva.Reserva_NumVal(txtNumero.getText()))
                            JOptionPane.showMessageDialog(null, "Numero de reserva garantido podemos prossegior com a reserva");
                        else
                             JOptionPane.showMessageDialog(null, "Numero da reserva já existe tente outro");
                    }
                }
        );
        
        Date data = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        
        txtDataIn.addActionListener(
            new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        objACtrReserva.Teste_dataIn(txtDataIn.getText());
                    }
                }
        );

        
        
        
        txtDataOut.addActionListener(
            new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            Vector quartosDisp = new Vector();
                            if(objACtrReserva.Teste_dataOut(txtDataIn.getText(), txtDataOut.getText())){
                                quartosDisp = objACtrReserva.VerificaDisp(txtDataIn.getText(), txtDataOut.getText());
                                if(objACtrReserva.Efe_Reserva(quartosDisp, txtcpf1.getText(), txtDataIn.getText(), txtDataOut.getText(), txtNumero.getText())){
                                    JOptionPane.showMessageDialog(null, "Reserva concluida com sucesso!");
                            }

                        }
                    }
                }
        );
        
        p21.add(jLabelNumero);
        p21.add(txtNumero);
        p21.add(jLabelDataIn);
        p21.add(txtDataIn);
        p21.add(JLabelDataOut);
        p21.add(txtDataOut);
        
        JPanel p22 = new JPanel();
        p22.setLayout(null);
        jLabelNome = new JLabel();
        jLabelTelefone = new JLabel();
        jLabelCep = new JLabel();
        jLabelCidade = new JLabel();
        jLabelRua = new JLabel();
        jLabelNumero = new JLabel();
        
        jLabelNome.setText("Nome");
        jLabelTelefone.setText("Telefone");
        jLabelCep.setText("CEP");      
        jLabelCidade.setText("Cidade");
        jLabelRua.setText("Rua");
        jLabelNumero.setText("Numero");
        
        jLabelNome.setBounds(10, 10, 240, 15);
        jLabelTelefone.setBounds(10, 40, 240, 15);
        jLabelCep.setBounds(10, 80, 240, 15);
        jLabelCidade.setBounds(10, 120, 240, 15);
        jLabelRua.setBounds(10, 160, 240, 15);
        jLabelNumero.setBounds(10, 200, 240, 15);
        
        
        txtNome11 = new JTextField(20);
        txtTelefone1 = new JTextField(20);
        txtCep1 = new JTextField(20);
        txtCidade1 = new JTextField(20);
        txtRua1 = new JTextField(20);
        txtNumero1 = new JTextField(20);
        
        txtNome11.setBounds(75, 10, 240, 20);
        txtTelefone1.setBounds(75, 40, 240, 20);
        txtCep1.setBounds(75, 80, 240, 20);
        txtCidade1.setBounds(75, 120, 240, 20);
        txtRua1.setBounds(75, 160, 240, 20);
        txtNumero1.setBounds(75, 200, 240, 20);
        JButton b19 = new JButton("Cadastrar");
        b19.setBounds(75, 280, 120, 40);
        p22.add(jLabelNome);
        p22.add(txtNome11);
        p22.add(jLabelTelefone);
        p22.add(txtTelefone1);
        p22.add(jLabelCep);
        p22.add(txtCep1);
        p22.add(jLabelCidade);
        p22.add(txtCidade1);
        p22.add(jLabelRua);
        p22.add(txtRua1);
        p22.add(jLabelNumero);
        p22.add(txtNumero1);
        p22.add(b19);
        
        b19.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cadCliente(txtcpf1.getText(), txtNome.getText(), txtTelefone.getText(), txtCep.getText(), txtCidade.getText(), txtRua.getText(), txtNumero.getText());
                }
            }
        );
        
        
        
        
        
        
        
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        // Adiciona os painéis e associa um nome (string) a eles
        cards.add("", p20);
        cards.add(Registrar_PANEL, p22);
        cards.add(Reserva_PANEL, p21);
        
        cards.setBounds(80, 100, 1000, 400);
        
        p2.add(cards);
        
        b2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(!objACtrCliente.CPF_Val(txtcpf1.getText())){
                        JOptionPane.showMessageDialog(null, "Cliente existe devemos fazer a reserva");
                        CardLayout layout = (CardLayout) cards.getLayout();
                        layout.show(cards, Reserva_PANEL);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Cliente não existe devemos fazer Cadastro");
                        CardLayout layout = (CardLayout) cards.getLayout();
                        layout.show(cards, Registrar_PANEL);
                    }
                }
            }
        );
        
        JPanel p3 = new JPanel();
        p3.setLayout(null);
        labelCpf2 = new JLabel("Digite o CPF para Proceguir");
        labelCpf2.setBounds(200, 10, 240, 15);
        txtcpf2 = new JTextField(20);
        txtcpf2.setBounds(400, 10, 240, 20);
        JButton b3 = new JButton("Verificar");
        b3.setBounds(680, 10, 240, 25);
        p3.add(labelCpf2);
        p3.add(txtcpf2);
        p3.add(b3);
        
         b3.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(!objACtrCliente.CPF_Val(txtcpf1.getText())){
                        FazerPagamento(txtcpf1.getText());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Cliente não existe. Tente denovo");
                    }
                }
            }
        );
        
        
        JPanel p4 = new JPanel();
                            
        p4.add(new JLabel("<html>"+objACtrQuarto.imprimeQuartos()+"</html>"));
        
        /*[1] Fazer Reserva \n"
                        + "[2] Adicionar cliente\n"
                        + "[3] Efetuar pagamento\n"*/
        
        tabbedPane.add(Registrar_PANEL, p1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        tabbedPane.add(Reserva_PANEL, p2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
        
        tabbedPane.add(Pagar_PANEL, p3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_C);
       
        
        //The following line enables to use scrolling tabs.
        
        int inset = 75;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tabbedPane.setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        //tabbedPane.setSize(1000, 600);
        tabbedPane.setLocation(20, 0);
        JanelaCliente.add(tabbedPane);
        return JanelaCliente;
    }
    
    private final String OpQuartos_PANEL = "Opções sobre quarto";
    private final String Desconto_PANEL = "Dar desconto";
    private final String Relatorios_PANEL = "Relatorios";
    private final String VisReserva_PANEL = "Visualizar Cliente";
    private final String VisCliente_PANEL = "Visualizar Reservas";
    JTabbedPane tabbedPane1 = new JTabbedPane();
   
    
    public JPanel FormlimGerente(){
        JPanel JanelaGerente = new JPanel();
        JanelaGerente.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        
        JPanel p11 = new JPanel();
        p11.add(new JLabel("<html>"+objACtrReserva.imprimeReservas()+"</html>"));
        
        JPanel p12 = new JPanel();
        p12.add(new JLabel("<html>"+objACtrCliente.imprimeClientes()+"</html>"));
        
        JPanel p13 = new JPanel();
        JButton bq1 = new  JButton("Cadastrar Quartos");
        bq1.setBounds(80, 20, 240, 25);
        JButton bq2 = new  JButton("Remover Quartos");
        bq2.setBounds(80, 60, 240, 25);
        JButton bq3= new  JButton("Visualizar Quartos");
        bq3.setBounds(80, 100, 240, 25);
        JButton bq4= new  JButton("alterar Quartos");
        bq4.setBounds(80, 140, 240, 25);
        
        bq1.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    cadQuarto();
                }
            }
        );
        
        bq2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    objACtrQuarto.RmQuarto();
                }
            }
        );
        
        bq3.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    visQuarto();
                }
            }
        );
        
        bq4.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    objACtrQuarto.AlteraQuarto();
                }
            }
        );
        
        p13.add(bq1);
        p13.add(bq2);
        p13.add(bq3);
        p13.add(bq4);
        
        JPanel p14 = new JPanel();
        final JTextField Descon = new JTextField(20);
        
        JButton bq0= new  JButton("Dar desconto");
        bq0.setBounds(80, 140, 240, 25);
        
        p14.add(Descon);
        p14.add(bq0);
        
        bq0.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    objACtrReserva.GeraDesconto(Integer.parseInt(Descon.getText()));
                    
                }
            }
        );
        
        JPanel p15 = new JPanel();
        JButton bq9= new  JButton("Relatórios");
        bq9.setBounds(80, 140, 240, 25);
        
        p15.add(bq9);
        
        bq9.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    try {
                        gerarRelatorios();
                    } catch (ParseException ex) {
                    }
                }
            }
        );
        
        
        tabbedPane1.add(VisCliente_PANEL, p11);
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_1);
        
        tabbedPane1.add(VisReserva_PANEL, p12);
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_1);
    
        
        tabbedPane1.add(OpQuartos_PANEL, p13);
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_1);

        
        tabbedPane1.add(Desconto_PANEL, p14);
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_1);
        
        
        tabbedPane1.add(Relatorios_PANEL, p15);
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_1);
        
       
        
        //The following line enables to use scrolling tabs.
        
        int inset = 200;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tabbedPane1.setBounds(inset, inset, screenSize.width  - inset*2, screenSize.height - inset*2);
        tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane1.setLocation(20, 0);
        p2.add(tabbedPane1);

        
        labelCpf3 = new JLabel("A senha para proceguir");
        labelCpf3.setBounds(200, 10, 240, 15);
        txtcpf3 = new JPasswordField(20);
        txtcpf3.setBounds(400, 10, 240, 20);
        JButton b6 = new JButton("Verificar");
        b6.setBounds(680, 10, 240, 25);
        JanelaGerente.add(labelCpf3);
        JanelaGerente.add(txtcpf3);
        JanelaGerente.add(b6);
        
         b6.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(txtcpf3.getText().equals("gerente")){
                        JOptionPane.showMessageDialog(null, "Acesso permitido");
                        CardLayout layout = (CardLayout) cards1.getLayout();
                        layout.show(cards1, "Gerente_PANEL");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Acesso negado tente novamente");
                        CardLayout layout = (CardLayout) cards.getLayout();
                        layout.show(cards, "");
                    }
                }
            }
        );
         
        cards1 = new JPanel();
        cards1.setLayout(new CardLayout());
        // Adiciona os painéis e associa um nome (string) a eles
        cards1.add("", p1);
        cards1.add( "Gerente_PANEL", p2);
        
        cards1.setBounds(80, 100, 1000, 400);
        
        JanelaGerente.add(cards1);
        
        
        return JanelaGerente;
    }
    
    private void gerarRelatorios () throws ParseException {
        boolean teste = true;
         while (teste) {
            int  intAOperacao1 = objALimPrincipal.montaMenuRel();
            switch (intAOperacao1) {
                case 1:
                    ImprimeRelatorioReservasCanceladas();          
                    break;
                case 2:
                    ImprimeRelatorioRelatorioReservasEfetivadas();	
                    break;
                case 3:
                    ImprimeRelatorioReservasNaoPagas();
                    break;
                case 4:
                    ImprimeRelatorioRelatorioReservasPeriodo();
                    break;
                case 5:
                    teste = false;
                    break;
            }
         }
    }
    
        private void ImprimeRelatorioReservasCanceladas() {
        String saidaStr =  objACtrReserva.RelatorioReservasCanceladas();;
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista Reservas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void ImprimeRelatorioRelatorioReservasEfetivadas() {
        String saidaStr =  objACtrReserva.RelatorioReservasEfetivadas();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void ImprimeRelatorioReservasNaoPagas (){
        String saidaStr =  objACtrReserva.RelatorioReservasNaoPagas();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void ImprimeRelatorioRelatorioReservasPeriodo() throws ParseException {
        String saidaStr =objACtrReserva.RelatorioReservasPeriodo();
        JTextArea saidaArea = new JTextArea();
        saidaArea.setText(saidaStr);
    	JOptionPane.showMessageDialog(null, saidaArea, "Lista Clientes", JOptionPane.INFORMATION_MESSAGE);
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
