
package Model;

import java.util.Date;
import java.util.Vector;
import java.io.Serializable;


public class entReserva implements Serializable{
    //Declaração dos atributos
    String cpfCliente;
    Date dataIn;
    Date dateOut;
    double desconto;
    Vector quartos = new Vector();
    String numero;
    entPagamento pagamento = new entPagamento();
    
    //construtores
    public entReserva (String pCpfCliente, Date pDateIn, Date pDateOut, double pDesconto, Vector pQuarto, String pNumero, entPagamento pPagamento){
        setCpFCliente (pCpfCliente);
        setDatain (pDateIn);
        setDataOut (pDateOut);
        setDesconto (pDesconto);
        setQuartos (pQuarto);
        setNumero (pNumero);
        setPagamento (pPagamento);
    }

    public entReserva() {
        
    }
    //gets e seters
    
    public void setCpFCliente (String pCpfCliente){
       cpfCliente = pCpfCliente;
    }
    
    public void setDatain (Date pDateIn) {
        dataIn = pDateIn;
    }
    public void setDataOut (Date pDateOut) {
        dateOut = pDateOut;
    }
    public void setDesconto (double pDesconto) {
        desconto = pDesconto;
    }
    
    public void setQuartos (Vector pQuartos) {
        quartos = pQuartos;
    }
    
    public void setNumero (String pNumero){
        numero = pNumero;
    }
    
    public void setPagamento (entPagamento pPagamento){
        pagamento = pPagamento;
    }
    
    public String getCpFCliente (){
        return cpfCliente;
    }
     
    public Date getDatain () {
        return dataIn;
    }
    public Date getDataOut () {
        return  dateOut;
    }
    public double getDesconto () {
         return desconto ;
    }
    
    public Vector getQuartos () {
         return quartos ;
    }
    
    public String getNumero () {
        return numero;
    }
    
    public entPagamento getPagamento (){
        return pagamento;
    }
   //outros metodos
}
