package Ejercicio1;

import Ejercicio1.model.Coche;
import Ejercicio1.model.Garaje;

public class Entrada {

    public static void main(String[] args) {

        Garaje garaje = new Garaje();
        Coche coche1 = new Coche("Opel", "Astra", "8660KHS");
        Coche coche2= new Coche("Seat", "Ibiza", "1716LYD");

        if (garaje.aceptarCoche(coche1)) {
            System.out.println("Coche aceptado: " + coche1.getModelo());
        } else {
            System.out.println("Garaje ocupado.");
        }

        if (garaje.aceptarCoche(coche2)) {
            System.out.println("Coche aceptado: " + coche2.getModelo());
        } else {
            System.out.println("Garaje ocupado.");
        }

        garaje.devolverCoche();

        if (garaje.aceptarCoche(coche2)) {
            System.out.println("Coche aceptado: " + coche2.getModelo());
        } else {
            System.out.println("Garaje ocupado.");
        }
    }
}
