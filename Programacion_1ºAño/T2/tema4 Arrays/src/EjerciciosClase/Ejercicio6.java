package EjerciciosClase;

import java.util.Scanner;

public class Ejercicio6 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        //6. (ArrayNoRepetidos): Crea un programa que pida por consola la longitud de un array. Una vez realizado esto:
        //    1. Pide al usuario el número máximo que va a guardar
        //    2. En el caso de que este número sea inferior a la longitud, seguirá pidiendo este dato hasta que se cumpla
        //    3. Cuando se cumpla la condición, se rellenará el array pero, sin números repetidos
        System.out.println("Introduce la longitud de un array: ");
        int longitud= lectorTeclado.nextInt();
        int[] array= new int[longitud];
        for (int i = 0; i < array.length; i++) {
            int max= array[longitud];
            if (max<longitud){
                max=array[longitud];
            }else {
                max=array[longitud];
            }
        }
    }
}
