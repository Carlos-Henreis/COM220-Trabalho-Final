
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
    
    //construtores
    public entReserva (String pCpfCliente, Date pDateIn, Date pDateOut, double pDesconto, Vector pQuarto){
        setCpFCliente (pCpfCliente);
        setDatain (pDateIn);
        setDataOut (pDateOut);
        setDesconto (pDesconto);
        setQuartos (pQuarto);
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
   //outros metodos
}
