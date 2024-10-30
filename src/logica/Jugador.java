
package logica;


public class Jugador implements Jugador1, Jugador2{
    
    private int dado1;
    private int dado2;
    private int cantMov;
    
    @Override
    public String PlayJugador(){
        dado1 = (int) (Math.random()*6+1);  ;
        dado2 =  (int) (Math.random()*6+1);   ;
        cantMov = dado1+dado2;
        return dado1 + " y " + dado2;
        
    }
    
    @Override
    public int cantMov(){
        return cantMov;
    }
    
}
