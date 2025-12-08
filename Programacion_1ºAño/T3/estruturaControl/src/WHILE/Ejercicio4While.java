package WHILE;

import java.util.Scanner;

public class Ejercicio4While {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numero;

        while (true) {
            // Pedimos al usuario que introduzca un número entero no negativo
            System.out.print("Introduce un número entero no negativo (o un número negativo para salir): ");
            numero = scanner.nextInt();

            // Si el número es negativo, terminamos el programa
            if (numero < 0) {
                System.out.println("Saliendo del programa...");
                break;
            }


            // Convertimos el número a binario, octal y hexadecimal
            String binario = Integer.toBinaryString(numero);
            String octal = Integer.toOctalString(numero);
            String hexadecimal = Integer.toHexString(numero);

            // Mostramos los resultados
            System.out.println("Número: " + numero);
            System.out.println("Binario: " + binario);
            System.out.println("Octal: " + octal);
            System.out.println("Hexadecimal: " + hexadecimal.toUpperCase()); // .toUpperCase() para mayúsculas


        }
    }
    }

