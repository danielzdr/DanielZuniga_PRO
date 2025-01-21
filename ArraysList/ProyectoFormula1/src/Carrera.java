import Controller.Coche;

import java.util.ArrayList;

public class Carrera {


    private int vueltas;
    private String nombre;
    private Coche ganador;

    private ArrayList<Coche> participantes;


    public Carrera(int vueltas, String nombre){
        this.vueltas=vueltas;
        this.nombre=nombre;
        participantes=new ArrayList<>();
    }

    public  void mostrarClasificacion(){

    }

    public void inscribirParticipante(Coche coche, int cv){
            if (participantes.size() == 8 || cv >= 300){
                System.out.println("No ha sido posible agregar el coche correctamente");
            }else {
                System.out.println("Ha sido agregado correctamente");
            }

    }

    public void mostrarGanador(){


    }

    public void descalificar(){

    }
}
