package EjercicioSimulacro3;

import java.util.Scanner;

public class Ejercicio1 {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        int numero=0;
        int sumaPares = 0;
        int sumaParesMedia = 0, contadorPares = 0;
        int mediaImpares = 0, contadorImpares = 0;

        while (numero>-1) {
            System.out.println("Introduce un numero mayor o igual a 0:");
            numero = lectorTeclado.nextInt();

        }
        int opcion;
        do {


            System.out.println("Suma de los numeros pares introducidos");
            System.out.println("Media de los numeros pares introducidos");
            System.out.println("Media de los numeros impares introducidos con dos decimales");
            System.out.println("Cuantos numeros hemos introducido");
            System.out.println("Cuantos numeros de los introducidos han sido ceros, cuantos pares y cuantos impares");
            System.out.println("Salir");

            System.out.println("Elige una opcion: ");
            opcion = lectorTeclado.nextInt();

            switch (opcion) {
                case 1:


                    if (numero % 2 == 0) {
                        sumaPares += numero;
                        System.out.println("Suma de los numeros pares introducidos" + sumaPares);
                    }
                    break;
                case 2:

                    if (numero % 2 == 0) {
                        sumaParesMedia += numero;
                        contadorPares++;

                    }
                    if (contadorPares > 0) {
                        double mediaPares = (double) sumaParesMedia / contadorPares;
                        System.out.printf("Media de los números pares: %.2f%n", mediaPares);
                    } else {
                        System.out.println("No se han introducido números pares");
                    }
                    break;
                case 3:

                    if (numero % 2 != 0) {
                        mediaImpares += numero;
                        contadorImpares++;
                        System.out.println("La media de los numeros impares" + mediaImpares + contadorImpares);
                    }
                    break;
                case 4:
                    System.out.println("Total de números introducidos: " + numero);
                    break;
                case 5:
                    int ceros = 0, pares = 0, impares = 0;
                    if (numero == 0) {
                        ceros++;
                    } else if (numero % 2 == 0) {
                        pares++;

                    } else
                        impares++;
                    System.out.println("Ceros introducidos: " + ceros);
                    System.out.println("Pares introducidos: " + pares);
                    System.out.println("Impares introducidos: " + impares);
                    break;
                case 6:
                    System.out.println("Salir ");
                    break;

                default:
                    System.out.println("Opcion no valida, elige otra opcion.... ");
                    break;
            }
        } while (opcion!=6);


    }

}
