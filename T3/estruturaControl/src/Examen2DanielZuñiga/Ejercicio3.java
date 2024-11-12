package Examen2DanielZuñiga;

import java.util.Scanner;

public class Ejercicio3 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {


        final int clave = 6666;
        int intentosRestantes = 4;
        boolean abierta = false;



        while (intentosRestantes > 0 && !abierta) {
            System.out.print("Introduce la clave de 4 digitos de la caja fuerte: ");
            int claveIngresada = lectorTeclado.nextInt();

            if (claveIngresada == clave) {
                System.out.println("Perfecto, la caja ha sido abierta");
                abierta = true;
            } else {
                intentosRestantes--;
                if (intentosRestantes > 0) {
                    System.out.println("Intento fallido, prueba de nuevo");
                } else {
                    System.out.println("Lo siento, caja bloqueada");
                }
            }
        }


    }
}
