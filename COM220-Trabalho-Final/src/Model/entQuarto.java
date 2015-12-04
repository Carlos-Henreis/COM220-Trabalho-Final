package Model;

import java.io.Serializable;

public class entQuarto implements Serializable{
     //Declaração dos atributos
    double preco;
    int numero;
    String descricao;
    
    //construtor
    public entQuarto (double pPreco, int pNumero, String pDescricao){
       setPreco (pPreco);
       setNumero (pNumero);
       setDescricao (pDescricao);
    }

    public entQuarto() {
        
    }
    //gets e seters
    public void setPreco (double pPreco) {
        preco = pPreco;
    }
    public void setNumero (int pNumero) {
        numero = pNumero;
    }
     public void setDescricao (String pDescricao) {
         descricao = pDescricao;
    }
     
    public double getPreco () {
       return preco;
    }
    public int getNumero () {
        return numero;
    }
     public String getDescricao () {
         return descricao ;
    }
}
