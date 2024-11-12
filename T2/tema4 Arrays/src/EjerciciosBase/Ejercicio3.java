package EjerciciosBase;

import java.util.Scanner;

public class Ejercicio3 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        //3. Escribe un programa que lea 10 números por teclado, los guarde en un array y que luego los muestre:
        //    - en orden inverso, es decir, el primero que se introduce es el último en mostrarse
        //    - en orden normal, es decir, el primero que se muestra es el primero que se ha introducido
            int [] numeros= new int[10];

        System.out.println("Introduce los 10 numeros: ");
        for (int i = 0; i < numeros.length; i++) {
            numeros[i]= lectorTeclado.nextInt();
        }
        System.out.println("\n Numeros en orden normal: ");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }

        System.out.println("\n Nummeros en orden inverso: ");
        for (int i = numeros.length -1; i >= 0; i--) {
            System.out.println(numeros[i]);
        }
    }
}
