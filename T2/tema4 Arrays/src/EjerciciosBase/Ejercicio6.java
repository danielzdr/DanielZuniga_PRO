package EjerciciosBase;

import java.util.Random;

public class Ejercicio6 {
    public static void main(String[] args) {
        int [] numeros=new int[20];
        Random random = new Random();

        for (int i = 0; i < numeros.length; i++) {
            numeros[i]= random.nextInt(30)+1;

        }
        System.out.println("Numeros originales: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();

        int modificaciones = 0;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == 6) {
                numeros[i] = 8;
                modificaciones++;
            }
        }

        // Modificamos todos los 7 por 15
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == 7) {
                numeros[i] = 15;
                modificaciones++;
            }
        }
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == 20) {
                numeros[i] = 10;
                modificaciones++;
            }
        }
        System.out.println("Numeros modificado:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Número total de modificaciones: " + modificaciones);

    }
}
