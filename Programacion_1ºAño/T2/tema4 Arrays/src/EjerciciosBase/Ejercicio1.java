package EjerciciosBase;

import java.util.Scanner;

public class Ejercicio1 {
    //Realiza un programa que pida 10 números por teclado
    // y que los almacene en un array. A continuación se mostrará el contenido
    // de ese array junto al índice (0 – 9)
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        int [] numeros=new int[10];
        for (int i = 0; i < 10; i++) {
            System.out.println("Introduce el numero");
            numeros[i]= lectorTeclado.nextInt();

        }

        for (int i = 0; i < numeros.length; i++) {
            System.out.printf("El numero en la posicion %d es %d\n",i+1,numeros[i]);
        }

    }
}
