package EjerciciosBase;

import java.util.Random;

public class Ejercicio2 {

    //Crear un programa que genere 30 números aleatorios entre 0 - 10 y los guarde en un array.
    //Una vez metidos, se deberá mostrar:
    // Numero de puntos obtenidos
    //Media de puntos obtenidos
    public static void main(String[] args) {

        int[] numeros = new int[30];
        Random random = new Random();
        int suma = 0;

        // Generar 30 números aleatorios entre 0 y 10 y guardarlos en el array
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = random.nextInt(10)+1;
            suma += numeros[i];
        }

        // Calcular la media de los puntos obtenidos
        double media = (double) suma / numeros.length;


        System.out.println("\nNumero total de puntos obtenidos son: "+suma);
        System.out.println("La media total de los puntos son: " + media);
    }
}

