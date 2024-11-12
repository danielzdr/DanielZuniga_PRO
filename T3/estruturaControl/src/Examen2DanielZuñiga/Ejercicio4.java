package Examen2DanielZuñiga;

import java.util.Scanner;

public class Ejercicio4 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

            double numeroDecimal=0;


            do {
                System.out.print("Introduce un número decimal positivo: ");
                numeroDecimal =lectorTeclado.nextInt();
                if (numeroDecimal < 0) {
                    System.out.println("El número debe ser positivo, intentalo de nuevo...");
                }
            } while (numeroDecimal < 0);

            int opcion;
            do {

                System.out.println("Selecciona la base a la que deseas convertir:");
                System.out.println("1. Base 2 ");
                System.out.println("2. Base 8 ");
                System.out.println("3. Base 16 ");
                System.out.println("4. Salir");


                System.out.print("Introduce la opción: ");
                String String = lectorTeclado.next();

                opcion=0;


                switch (opcion) {
                    case 1:
                        System.out.println("Número en Base 2: " + Integer.toBinaryString((int)numeroDecimal));
                        break;
                    case 2:
                        System.out.println("Número en Base 8: " + Integer.toOctalString((int)numeroDecimal));
                        break;
                    case 3:
                        System.out.println("Número en Base 16: " + Integer.toHexString((int)numeroDecimal).toUpperCase());
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción válida.");
                }
            } while (opcion != 5);
    }
}
