package EjerciciosClase;

import java.util.Scanner;

public class Ejercicio9 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        //9. (InvisibleArray) Desarrolla un programa que simule el sorteo de un amigo invisible. Para ello el programa debe:
        //- pedir el número de participantes (de ser impar saltará un fallo)
        //- pedir el nombre de cada uno de los participantes
        //- sacar por pantalla el orden de las parejas resultantes
        System.out.println("Introduce el numero de participantes: ");
        int participantes= lectorTeclado.nextInt();

            if (participantes % 2 != 0) {
                System.out.println("Error: introduce un numero valido");
            } else {
                System.out.println("Numero valido");
            }

        System.out.println("Introduce la longitud: ");
        int longitud= lectorTeclado.nextInt();
        int [] numeros=new int[longitud];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i]= numeros.length;
        }
        System.out.println("Introduce un nombre: ");
        String nombre= lectorTeclado.next();

    }
}
