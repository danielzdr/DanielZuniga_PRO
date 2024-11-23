package EjerciciosBase;

import java.util.Random;

public class Ejercicio6 {
    public static void main(String[] args) {
        //6. Escribe un programa que rellene un array de 20 números aleatorios entre el 0 y el 30. Una vez hecho esto realizará las siguientes operaciones:
        //    - modificar todos los 6 por 8
        //    - modificar todos los 7 por 15
        //    - modificar todos los 20 por 10
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
