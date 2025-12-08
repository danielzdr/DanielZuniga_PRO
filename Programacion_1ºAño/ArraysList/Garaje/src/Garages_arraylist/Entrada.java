package Garages_arraylist;

import Garages_arraylist.controller.Garaje;

public class Entrada {
    //VISTA lo que estoy viendo desde la consola
    public static void main(String[] args) {
        Garaje garaje=new Garaje();
        Garaje g2=new Garaje();
        Garaje g3 = new Garaje();
        Garaje g4= new Garaje();
        System.out.println("Bienvenidos a la aplicacion de garaje");
        garaje.anadirCoche("Mercedes","c220","1234A",200);
        garaje.anadirCoche("Ford","fiesta","1234B",100);
        g2.mostrarCoche();
        g3.listarMarca("Mercedes");
        g4.gestionCoche();
    }
}
