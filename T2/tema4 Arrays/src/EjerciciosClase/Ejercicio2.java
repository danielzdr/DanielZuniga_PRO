package EjerciciosClase;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        //2. (MayorArray) Crea una aplicación que realice lo siguiente:
        //    - pida por pantalla la longitud del array de enteros
        //    - crea el array de enteros con la longitud introducida
        //    - pida al usuario que introduzca todos los datos del array
        //    - saque por pantalla el elemento mayor y el elemento menor
        int longitud= lectorTeclado.nextInt();
        int [] numeros=new int[longitud];

        for (int i = 0; i < longitud ; i++) {
            System.out.println("Introduce los numeros: ");
            numeros[i]= lectorTeclado.nextInt();
        }

        int mayor=numeros[0];
        int menor=numeros[0];

        for(int item:numeros){
            if (item<menor){
                menor=item;
            }
            if (item>mayor){
                mayor=item;
            }
        }
        System.out.println("El nuemro mas grande es "+mayor);
        System.out.println("El nummero mas pequeño es "+menor);

    }
}
