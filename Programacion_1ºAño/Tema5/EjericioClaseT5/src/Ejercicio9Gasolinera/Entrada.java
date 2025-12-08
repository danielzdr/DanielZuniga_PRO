package Ejercicio9Gasolinera;

import Ejercicio9Gasolinera.model.Coche;
import Ejercicio9Gasolinera.model.Surtidor;

public class Entrada {

    public static void main(String[] args) {
        Surtidor surtidor= new Surtidor(50,"98");
        Coche coche = new Coche("98");
        coche.ponerGasolina(surtidor, 40);
        System.out.println("El coche ahora tiene " + coche.getLitrosDeposito() + " litros en el deposito");
    }
}
