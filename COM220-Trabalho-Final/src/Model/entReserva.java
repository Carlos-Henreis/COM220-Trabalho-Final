
package Model;

import java.util.Date;


public class entReserva {
        //Declaração dos atributos
    Date dataIn;
    Date dateOut;
    String descricao;
    
    //construtores
    public entReserva (Date pDateIn, Date pDateOut, String pDescricao){
       setDatain (pDateIn);
       setDataOut (pDateOut);
       setDescricao (pDescricao);
    }
    //gets e seters
    public void setDatain (Date pDateIn) {
        dataIn = pDateIn;
    }
    public void setDataOut (Date pDateOut) {
         dateOut = pDateOut;
    }
     public void setDescricao (String pDescricao) {
         descricao = pDescricao;
    }
     
    public Date getDatain () {
        return dataIn;
    }
    public Date getDataOut () {
        return  dateOut;
    }
     public String getDescricao () {
         return descricao ;
    }
   //outros metodos
}
