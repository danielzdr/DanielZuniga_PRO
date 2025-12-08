package Ejercicio1.model;
import java.util.Random;

public class Garaje {
    private Coche coche;
    private String averiaAsociada;
    private Coche cocheActual;
    Random random=new Random();
    public Garaje(){
        this.averiaAsociada = "Sin definir";
        this.random = new Random();
        this.cocheActual = null;
    }

    public boolean aceptarCoche(Coche coche){
        if (cocheActual==null){
            cocheActual=coche;
            cocheActual.agregarAveria("aceite");
        }
        this.cocheActual=coche;
        coche.agregarAveria("Aceite");
        return false;
    }

    public void devolverCoche(){
        this.cocheActual=null;
    }


}
