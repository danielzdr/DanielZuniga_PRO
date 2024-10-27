package EjercicioSimulacro3;

import java.util.Scanner;

public class Ejercicio4 {
    static Scanner lectorTeclado= new Scanner(System.in);
    public static void main(String[] args) {
        String palabraInversa = "";
        String palabra = lectorTeclado.next();
        if (palabra.length() < 4) {
            palabra = palabra.toUpperCase();
        } else if (palabra.length() < 8) {
            palabra = palabra.toLowerCase();
        } else if (palabra.length() < 11) {
            palabra = palabra.replaceAll("a", "á")
                    .replaceAll("e", "é")
                    .replaceAll("i", "í")
                    .replaceAll("o", "ó")
                    .replaceAll("u", "ú");
        } else {
            palabraInversa = "";
            for (int i = palabra.length()-1; i >=0 ; i--) {
                palabraInversa += palabra.charAt(i);
            }
        }

        if (palabra.length()>=11){
            System.out.println(palabraInversa);
        } else {
            System.out.println(palabra);
        }
    }

}
