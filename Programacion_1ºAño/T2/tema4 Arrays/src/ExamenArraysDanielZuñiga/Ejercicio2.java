package ExamenArraysDanielZuñiga;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        Random random=new Random();
        System.out.print("Ingrese la longitud de datos que desea guardar: ");
        int longitud = scanner.nextInt();


        int[] numeros = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            numeros[i] = random.nextInt(20)+1;
        }


        System.out.println("Mostrra lista:");
        for (int item : numeros) {
            System.out.print(item + " ");
        }
        System.out.println();


        System.out.println("Mostrar extremos");
        int max = numeros[0];
        int min = numeros[0];
        for (int item : numeros) {
            if (item > max) {
                max = item;
            } else if (item< min) {
                min=item;
            }
        }
        System.out.println("Número más grande: " + max);
        System.out.println("Número más pequeño: " + min);


        System.out.println("Mostrar par o impar");
        int pares = 0, impares = 0;
        for (int item : numeros) {
            if (item % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }
        System.out.println("Cantidad de pares: " + pares);
        System.out.println("Cantidad de impares: " + impares);


        System.out.println("Mostrar repeticiones");
        System.out.print("Ingrese un número para verificar cuántas veces aparece: ");
        int numeroBuscado = scanner.nextInt();
        int repeticiones = 0;
        for (int item : numeros) {
            if (item == numeroBuscado) {
                repeticiones++;
            }
        }
        System.out.println("El número " + numeroBuscado + " aparece " + repeticiones + " veces.");
    }
    }

