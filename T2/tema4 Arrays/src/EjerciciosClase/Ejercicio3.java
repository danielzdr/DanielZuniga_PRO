package EjerciciosClase;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio3 {
    //3. (SumaArrays) Crear una aplicación que:
    //    - pida la longitud de dos arrays de enteros
    //    - crea los array de enteros con las longitudes introducidas
    //    - rellena ambos arrays con números aleatorios
    //    - crea un tercer array de la misma longitud que los dos anteriores
    //    - rellena el tercer array con la suma de las posiciones (Array1.posicion1 + array2.posicion = array3.posicion)
    static Scanner lectorTeclado=new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        System.out.println("Introduice la longitud de loa arrays: ");
        int longitud= lectorTeclado.nextInt();
        int [] array1= new int[longitud];
        int [] array2= new int[longitud];
        int [] sumaArray=new int[longitud];

        for (int i = 0; i < longitud; i++) {
              array1[i]= random.nextInt(100) ;
              array2[i]=random.nextInt(100);
        }
        for (int i = 0; i < longitud; i++) {
            sumaArray[i]=array1[i]+array2[i];
        }

        System.out.println(array1);
        System.out.println(array2);
        System.out.println(sumaArray);
    }
}
