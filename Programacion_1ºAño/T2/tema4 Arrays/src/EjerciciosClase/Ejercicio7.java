package EjerciciosClase;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
        // Array de caracteres con todas las letras del abecedario
        char[] abecedario = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        // Configuración del escáner para la entrada de datos
        Scanner scanner = new Scanner(System.in);

        // Preguntar al usuario cuántas palabras desea generar
        System.out.print("¿Cuántas palabras quieres guardar?: ");
        int numPalabras = scanner.nextInt();

        // Crear el array de Strings con la longitud especificada por el usuario
        String[] palabras = new String[numPalabras];

        // Objeto Random para generar números aleatorios
        Random random = new Random();

        // Generar cada palabra
        for (int i = 0; i < numPalabras; i++) {
            System.out.print("¿Cuál es la longitud de la palabra " + (i + 1) + "?: ");
            int longitudPalabra = scanner.nextInt();

            // Crear palabra aleatoria con la longitud especificada
            StringBuilder palabra = new StringBuilder();
            for (int j = 0; j < longitudPalabra; j++) {
                // Escoger una letra aleatoria del array de abecedario
                char letraAleatoria = abecedario[random.nextInt(abecedario.length)];
                palabra.append(letraAleatoria);
            }

            // Guardar la palabra generada en el array de palabras
            palabras[i] = palabra.toString();
        }

        // Imprimir las palabras generadas
        System.out.println("Las palabras generadas son:");
        for (int i = 0; i < palabras.length; i++) {
            System.out.println((i + 1) + ". " + palabras[i]);
        }

        // Cerrar el escáner
        scanner.close();
    }
    }

