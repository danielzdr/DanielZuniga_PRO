package EjerciciosClase;

import java.util.Scanner;

public class Ejercicio4 {
   static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        //4. (PosicionesArray) Crea una aplicación que:
        //    - pida por teclado la longitud de un array
        //    - cree el array de enteros de la longitud introducida
        //    - pida al usuario que introduzca todos los datos del array
        //    - mueva una posición todos los elementos. el elemento de la posición 0 pasa a la posición 1 y así sucesivamente. El elemento que está en la última posición pasa a la primera
        //    - realizar la tarea anterior pero al contrario. El elemento que está en la primera posición pasa a la última y así sucesivamente
        System.out.println("Introduce la longitud:");
        int longitud= lectorTeclado.nextInt();
        System.out.println("Introduce los datos del array: ");
       int [] array=new int[longitud];

       rotarAdelante(array);
        System.out.println("Array despues de rotar hacia delante "+array);

        rotarAtras(array);
        System.out.println("Array depues de rotar hacia atras "+array);

    }

    public static void rotarAdelante(int[]array) {
        int ultimo=array[array.length-1];
        for (int i = 0; i < array.length - 1; i++) {
            array[i]= array[array.length-1];
        }
        array[0]=ultimo;
    }

    public static void rotarAtras(int[] array) {
        int primero= array[0];
        for (int i = array.length -1; i < 0; i--) {
            array[i]= array[i-1];
        }
        array[array.length-1]=primero;
    }
}
