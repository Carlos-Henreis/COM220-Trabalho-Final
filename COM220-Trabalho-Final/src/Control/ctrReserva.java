
package Control;

import Model.*;
import View.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ctrReserva {
    //Declaração dos atributos
    private limReserva objLimReserva = new limReserva();
    private entReserva objEntReserva;
    private ctrPrincipal objCtrPrincipal;
    private Vector vecADadosForm = new Vector();
    private Vector vecReserva = new Vector();
    private Vector vecReservaCancl = new Vector();
    private Vector vecReservaEfetivadas = new Vector();
    private Vector vecQuartosDisp;
    private double desconto = 0;
    private entPagamento pagamento;
    private final String arquivo = "Reservas_cad.dat";
    private double valorReserva = 0;
    
    public ctrReserva(ctrPrincipal pCtrPrincipal) throws Exception {
        objCtrPrincipal = pCtrPrincipal;
        desserializaReserva();;
    }
    
    public boolean IniciarReserva(String pCpfCliente, String pDatain, String pDataOut, String pNumero) {
        try{
            Date hoje = new Date();
            Date DateIn, DataOut;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateIn = formatter.parse(pDatain);
            DataOut = formatter.parse(pDataOut);
            objEntReserva= new entReserva();
            objEntReserva.setCpFCliente(pCpfCliente);
            objEntReserva.setDatain(DateIn);
            objEntReserva.setDataOut(DataOut);
            objEntReserva.setDesconto(desconto);
            objEntReserva.setNumero(pNumero);
            return true;
        }catch (ParseException e) {      
        } 
        return false;
    }
    public boolean Reserva_NumVal (String pNumero){
        for (int i =0; i<vecReserva.size(); i++){
            if(pNumero.equals(((entReserva)vecReserva.elementAt(i)).getNumero())){
                objLimReserva.montaFormaNum();
                return true;
            }
        }
        return false;
    }
    
    public boolean Teste_dataIn (String pDataIn){
        try{
            Date hoje = new Date();
            Date DateIn;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateIn = formatter.parse(pDataIn);
            if (0<(int) ((hoje.getTime() - DateIn.getTime()) / 86400000L)){
                objLimReserva.montaFormaDataIn(hoje);
                return false;
            }
        }catch (ParseException e) {      
        } 
         return true;        
    }
    
    public boolean Teste_dataOut(String pDataIn, String pDataOut){
        try{
            Date hoje = new Date();
            Date DateIn, DataOut;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateIn = formatter.parse(pDataIn);
            DataOut = formatter.parse(pDataOut);
            if (0<(int) ((DateIn.getTime() - DataOut.getTime()) / 86400000L)){
                objLimReserva.montaFormaDataOut(DateIn);
                return false;
            }
        }catch (ParseException e) {      
        } 
         return true;        
    }
    
    
    public boolean Efe_Reserva (Vector QuartosD, String pCpfCliente, String pDatain, String pDataOut, String pNumero) {
        try{
            Date hoje = new Date();
            Date DateIn, DataOut;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateIn = formatter.parse(pDatain);
            DataOut = formatter.parse(pDataOut);
            objEntReserva= new entReserva();
            objEntReserva.setCpFCliente(pCpfCliente);
            objEntReserva.setDatain(DateIn);
            objEntReserva.setDataOut(DataOut);
            objEntReserva.setDesconto(desconto);
            objEntReserva.setNumero(pNumero);
            vecQuartosDisp = objLimReserva.montaFormDadosQuartos(QuartosD);
            if (vecQuartosDisp != null){
                objEntReserva.setQuartos(vecQuartosDisp);
                addVetor(objEntReserva);
                valorReserva = objCtrPrincipal.getObjCtrPagamento().calculaValor (objEntReserva, desconto);
                pagamento = new entPagamento();
                pagamento.setSitucao(valorReserva*-1);
                pagamento.setValor(valorReserva);
                objEntReserva.setPagamento(pagamento);
                if (((int) ((hoje.getTime() - objEntReserva.getDatain().getTime()) / 86400000L)) > 3){//O cliente deve efetuar o pagamento no ato senão a reserva vai ser cancelada
                    objLimReserva.montaFormaNovoPagamento();
                    objCtrPrincipal.getObjCtrPagamento().registrarPagamento(pCpfCliente);
                }

                return true;
            }
        }catch (Exception e) {      
        } 
        return false;
    }

    private boolean Cliente_Cad(String pCpfClinte) {
        Vector Clientes = new Vector();
        Clientes =  objCtrPrincipal.getObjCtrCliente().getListaClientes();
        for (int i = 0; i < Clientes.size(); i++){
             if(pCpfClinte.equals(((entCliente)Clientes.elementAt(i)).getCpf()))
                 return true;
        }
        return false;
    }
    
    public Vector VerificaDisp(String pDtaIn, String pDtaOut){
        try{
            Date hoje = new Date();
            Date pDataIn, pDataOut;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            pDataIn = formatter.parse(pDtaIn);
            pDataOut = formatter.parse(pDtaOut);
            vecQuartosDisp = new Vector();
            Vector Quartos = new Vector();
            Vector QuartosR = new Vector();
            Vector aux = new Vector ();
            //Encontrar toda os quartos com reserva
            for (int l = 0; l < vecReserva.size(); l++){
                    Quartos = ((entReserva)vecReserva.elementAt(l)).getQuartos();
                    for (int z = 0; z < Quartos.size(); z++){
                        QuartosR.add(Quartos.elementAt(z));
                    }
            }        
            //Lista todos os quartos sem reserva
            Quartos = objCtrPrincipal.getObjCtrQuarto().getListaQuartos();
            for (int k = 0; k <Quartos.size(); k++){
                boolean reservado = false;
                for (int z = 0; z < QuartosR.size(); z++){
                   if (((entQuarto)Quartos.elementAt(k)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(z)))
                        reservado = true;
                }
                if (!reservado){
                    vecQuartosDisp.add(Quartos.elementAt(k));
                }
            }
            //Lista todos os quartos reservados que não podem serem adicionados na nova reserva (aux)
            for (int z = 0; z < vecReserva.size(); z++) {
                Date a= new Date ();
                Date b = new Date();
                a = ((entReserva)vecReserva.elementAt(z)).getDatain();
                b = ((entReserva)vecReserva.elementAt(z)).getDataOut();
                QuartosR = ((entReserva)vecReserva.elementAt(z)).getQuartos();
                //Os quartos dessas reservas não podem ser reservados na nova reserva
                if ((a.after(pDataIn) && a.before(pDataOut))
                   || (b.after(pDataIn) && b.before(pDataOut))
                   || (a.before(pDataIn) && b.after(pDataOut))
                   || (a.equals(pDataIn) || b.equals(pDataIn)
                   || (a.equals(pDataIn) || b.equals(pDataOut)))) {
                    for (int i = 0; i < QuartosR.size(); i++)
                        for (int l = 0; l < Quartos.size(); l++)
                            if (((entQuarto)Quartos.elementAt(l)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                                aux.add(Quartos.elementAt(l));

                } 

            }

            //adiciona aos vecQuartosDisp os quartos reservados que podem ser adicionados no periodo da nova reserva 

             for (int z = 0; z < vecReserva.size(); z++) {
                Date a= new Date ();
                Date b = new Date();
                a = ((entReserva)vecReserva.elementAt(z)).getDatain();
                b = ((entReserva)vecReserva.elementAt(z)).getDataOut();
                QuartosR = ((entReserva)vecReserva.elementAt(z)).getQuartos();
                //Os quartos dessas reservas não podem ser reservados na nova reserva
                if ((a.after(pDataIn) && a.before(pDataOut))
                   || (b.after(pDataIn) && b.before(pDataOut))
                   || (a.before(pDataIn) && b.after(pDataOut))
                   || (a.equals(pDataIn) || b.equals(pDataIn)
                   || (a.equals(pDataIn) || b.equals(pDataOut)))) {

                }
                else {
                    for (int i = 0; i < QuartosR.size(); i++){
                        boolean quarto_r = false;
                        for (int l = 0; l < vecQuartosDisp.size(); l++)
                            if (((entQuarto)vecQuartosDisp.elementAt(l)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                                quarto_r = true;
                        if (!quarto_r)
                            for (int k = 0; k <Quartos.size(); k++)
                                if (((entQuarto)Quartos.elementAt(k)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i))){
                                    boolean m = true;
                                    for (int c = 0; c < aux.size(); c++) {
                                        if (((entQuarto)aux.elementAt(c)).getNumero() == Integer.parseInt((String)QuartosR.elementAt(i)))
                                            m = false;
                                    }
                                    if (m)
                                        vecQuartosDisp.add(Quartos.elementAt(k));
                                }


                    }
                }
            }
        }catch (ParseException e) {      
        } 
        return vecQuartosDisp;
    }
    
    private void salva() {
  
    }
    
    public void ReservaEfetivadas () {//Adciona na lista as reservas que foram fetivadas no dia
         Date hoje = new Date ();
         for (int z = 0; z < vecReserva.size(); z++) {
            if (((int) ((((entReserva)vecReserva.elementAt(z)).getDatain().getTime() - (hoje.getTime())) / 86400000L)) == 0){// Reserva será relaizada hj
                if (((entReserva)vecReserva.elementAt(z)).getPagamento().getSituacao() == 0){//Cliente fez todo o pagamento e o quarto será
                     vecReservaEfetivadas.add(vecReserva.elementAt(z));
                 }
                 
            }
         }
    }
     
    public void CancelarReservas() {
         Date hoje = new Date ();
         for (int z = 0; z < vecReserva.size(); z++) {
            if (((int) ((((entReserva)vecReserva.elementAt(z)).getDatain().getTime() - (hoje.getTime())) / 86400000L)) > 3){
                 int dias = (int) ((((entReserva)vecReserva.elementAt(z)).getDataOut().getTime() - ((entReserva)vecReserva.elementAt(z)).getDatain().getTime()) / 86400000L);
                 if (!situacaoPagamento(((entReserva)vecReserva.elementAt(z)).getPagamento(), dias)){
                     vecReservaCancl.add(vecReserva.elementAt(z));
                     vecReserva.removeElementAt(z);
                 }
            }
         }
         
    }
    
    private boolean situacaoPagamento (entPagamento pPagamento, int dias) {
        double diaria = pPagamento.getPreco()/dias;
        if (pPagamento.getSituacao()*-1 < diaria)
            return false;
        return true;
    }
    
    public void GeraDesconto (double pDesconto){
        desconto = pDesconto;
        objLimReserva.geraFomaDesconto();
        
    }
     
    public void addVetor(entReserva pReserva) {
        vecReserva.add(pReserva);
    }

    private void serializaReserva() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(vecReserva);
        objOS.flush();
        objOS.close();
    }
    
    private void desserializaReserva() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            vecReserva = (Vector) objIS.readObject();
            objIS.close();
        }
    }
    
    public Vector getListaReservas() {
        return vecReserva;
    }
     
    public void finalize() throws Exception {
        serializaReserva();
    }
    
    public String RelatorioReservasCanceladas() {
        objEntReserva = new entReserva ();
        int total = 0;
        String resultado = "";
        Date hoje = new Date ();
        if (vecReservaCancl.size() == 0){
            resultado += "<br><b>Não existe nenhuma Reserva Cancelada no dia: "+hoje+"\n";
            return resultado;
                    
        }
        resultado += "<br><b>Reservas Canceladas no dia: "+hoje+"\n";
        for (int intIdx = 0; intIdx < vecReservaCancl.size(); intIdx++) {
            objEntReserva = (entReserva) vecReservaCancl.elementAt(intIdx);
            if (((int) ((objEntReserva.getDatain().getTime() - (hoje.getTime())) / 86400000L)) <= 3){//Condição suficiente para que a reserva fio cancelada hj
              
                resultado += "<br><b>Reserva :</b> " + objEntReserva.getNumero();
                resultado += "<br><b>Feita por :</b> " + objEntReserva.getCpFCliente();
                resultado += "<hr><br>";
                total++;
            }
        }
        resultado += "Total de reservas canceladas hoje"+total;
        return resultado;
    }
    
    public String RelatorioReservasEfetivadas() {
        objEntReserva = new entReserva ();
        int total = 0;
        String resultado = "";
        Date hoje = new Date ();
        if (vecReservaEfetivadas.size() == 0){
            resultado += "<br><b>Não existe nenhuma Reserva efetiva para hoje: "+hoje+"\n";
            return resultado;
                    
        }
        resultado += "<br><b>Reservas Efetivadas no dia: "+hoje+"\n";
        for (int intIdx = 0; intIdx < vecReservaEfetivadas.size(); intIdx++) {
            if (((int) ((((entReserva)vecReservaEfetivadas.elementAt(intIdx)).getDatain().getTime() - (hoje.getTime())) / 86400000L)) == 0){
                objEntReserva = (entReserva) vecReservaEfetivadas.elementAt(intIdx);
                resultado += "<br><b>Reserva :</b> " + objEntReserva.getNumero();
                resultado += "<br><b>Feita por :</b> " + objEntReserva.getCpFCliente();
                resultado += "<hr><br>";
                total++;
            }
        }
        resultado += "Total de reservas efetivadas hoje"+total;
        return resultado;
    }
    
     public String RelatorioReservasNaoPagas() {
        objEntReserva = new entReserva ();
        int total = 0;
        String resultado = "";
        Date hoje = new Date ();
        if (vecReserva.size() == 0){
            resultado += "<br><b>Não Existe reservas\n";
            return resultado;        
        }
        resultado += "<br><b>Reservas não pagas até hoje dia: "+hoje+"\n";
        for (int intIdx = 0; intIdx < vecReserva.size(); intIdx++) {
            objEntReserva = (entReserva) vecReserva.elementAt(intIdx);
            if (objEntReserva.getPagamento().getSituacao()< 0){
                resultado += "<br><b>Reserva :</b> " + objEntReserva.getNumero();
                resultado += "<br><b>Feita por :</b> " + objEntReserva.getCpFCliente();
                resultado += "<br><b>tota a pagar:</b>"+ objEntReserva.getPagamento().getPreco();
                resultado += "<br><b>Situação:</b>"+ objEntReserva.getPagamento().getSituacao();
                resultado += "<hr><br>";
                total++;
            }
        }
        resultado += "Total de reservas efetivadas hoje"+total;
        return resultado;
    }
    
    public String RelatorioReservasPeriodo() throws ParseException {
        objEntReserva = new entReserva ();
        Date inicio = new Date();
        Date fim = new Date();
        int total_Canceladas = 0;
        int total_Efetivadas = 0;
        String resultado = "";
        vecADadosForm = objLimReserva.MontaFormperiodo();
        inicio = (Date)vecADadosForm.elementAt(0);
        fim = (Date)vecADadosForm.elementAt(1);
        if (vecReservaCancl.size() == 0 && vecReservaEfetivadas.size() == 0){
            resultado += "Não Existe reservas\n";
            return resultado;
                    
        }
        resultado += "\nRelátorio de reservas do Periodo: "+inicio+" | "+fim+"\n";
        resultado += "\nReservas Efetivadas no Periodo: \n";
        for (int intIdx = 0; intIdx < vecReservaEfetivadas.size(); intIdx++) {
            if (inicio.before(((entReserva)vecReservaEfetivadas.elementAt(intIdx)).getDatain()) && fim.after(((entReserva)vecReservaEfetivadas.elementAt(intIdx)).getDataOut())){
                objEntReserva = (entReserva) vecReservaEfetivadas.elementAt(intIdx);
                resultado += "\nReserva :" + objEntReserva.getNumero();
                resultado += "\nFeita por : " + objEntReserva.getCpFCliente();
                resultado += "\n-----------------\n";
                total_Efetivadas++;
            }
        }
         resultado += "\n-----------------\n";
        resultado += "\nReservas Canceladas no Periodo: \n";
         
        for (int intIdx = 0; intIdx < vecReservaCancl.size(); intIdx++) {
            objEntReserva = (entReserva) vecReservaCancl.elementAt(intIdx);
             if (inicio.before(((entReserva)vecReservaEfetivadas.elementAt(intIdx)).getDatain()) && fim.after(((entReserva)vecReservaEfetivadas.elementAt(intIdx)).getDataOut())){
                resultado += "\nReserva : " + objEntReserva.getNumero();
                resultado += "\nFeita por : " + objEntReserva.getCpFCliente();
                resultado += "\n-----------------\n";
                total_Canceladas++;
            }
        }
        resultado += "\nTotal de reservas efetivadas no periodo"+total_Efetivadas;
        resultado += "\nTotal de reservas canceladas no periodo"+total_Canceladas;
        return resultado;
    }
    
    
    

    public String imprimeReservas() {
        Vector Quartos = new Vector();
        String result = "";
        if (vecReserva.size()==0)
            result+="Não existem Reservas cadastrados\n";
        else{
            result +=  "<CENTER><FONT COLOR=BLUE SIZE=6>Todas as Reservas Cadastradas</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Reserva </TD> "
                    + "<TD>Feita por </TD>"
                    + "<TD>Data de entrada </TD>"
                    + "<TD>Data de Saida </TD>"
                    + "<TD>Preço</TD>"
                    + "<TD>Divida do cliente</TD>"
                    + "<TD>Quartos da reserva</TD>"
                    + " </TR>";
            for (int i = 0; i < vecReserva.size(); i++) {
                result += "<TR>"+"<TD>"+((entReserva) vecReserva.elementAt(i)).getNumero()+"</TD><TD>"+ ((entReserva) vecReserva.elementAt(i)).getCpFCliente() +"</TD><TD>" + ((entReserva) vecReserva.elementAt(i)).getDatain() + "</TD><TD>" + ((entReserva) vecReserva.elementAt(i)).getDataOut() +"</TD><TD>"+((entReserva) vecReserva.elementAt(i)).getPagamento().getPreco()+"</TD><TD>"+((entReserva) vecReserva.elementAt(i)).getPagamento().getSituacao()+"</TD><TD>";
                Quartos = ((entReserva) vecReserva.elementAt(i)).getQuartos();
                for (int z = 0; z < Quartos.size(); z++) {
                    result += (String)Quartos.elementAt(z)+"<br>";
		}
                result += "</TD>";
            }
            result += "</TD></TR></TABLE>";
        }
		return result;
    }
    
}