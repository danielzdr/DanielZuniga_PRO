package Ejemplo;

import Ejemplo.model.ClasePadre;

public class Entrada {
    public static void main(String[] args) {

        ClasePadre clasePadre= new ClasePadre("Pablos","Soriano", 15, "pablos.soriano@gmail.com");
        ClasePadre.ClaseHija claseHija= clasePadre. new ClaseHija("Ruben", "Parra", 13,"Ruben.parra@gmail.com");
        clasePadre.setClaseHija(claseHija);
        clasePadre.mostrarDatos();


    }
}
