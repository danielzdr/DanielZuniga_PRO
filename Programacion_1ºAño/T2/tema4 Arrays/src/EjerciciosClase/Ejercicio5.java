package EjerciciosClase;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio5 {
    //5. (OperacionesArray) Crear un array con 10 posiciones y asígnale números enteros entre 0 y 9, ambos inclusive (de forma automática)
    // Se mostrarán los datos separados por una coma y un espacio. Una vez hecho esto se modificará el array del de la manera indicada en cada apartado, y se mostrará de nuevo.
    //- Incrementar en 1 los valores pares y en -1 los impares.
    //- Duplicar los valores positivos menores que 5
    //- Sumar a cada valor un valor entero aleatorio entre -5 y 5.
    //- Mover los datos una posición hacia la derecha (el primero pasa al segundo, el
    //segundo al tercero, ..., y el último al primero).
    //- Intercambiar el primero con el segundo, el tercero con el cuarto, ..., el penúltimo con el último.
    //- Invertir el array
    //- Mostrar la posición del primer par y del último impar.
    public static void main(String[] args) {
        Scanner ala = new Scanner(System.in);
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            System.out.println("Numero " + (i + 1));
            array[i] = ala.nextInt();
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                array[i] = array[i] + 1;
                System.out.print("Nuevo(par) " + array[i] + " \t");
            } else {
                array[i] = array[i] - 1;
                System.out.print("Nuevo(impar) " + array[i] + " \t");
            }
            if (array[i] < 5 && array[i] >= 0) {
                array[i] = array[i] * 2;
                System.out.print("Duplicado " + array[i] + " \t");
            }
            int ram = (int) (Math.random() * 11) - 5;
            array[i] = array[i] + ram;
            System.out.print("Condicionado entre 5 y -5: " + array[i] + "\n");
        }
        int guardado = array[array.length - 1];
        for (int j = array.length - 1; j > 0; j--) {
            array[j] = array[j - 1];
        }
        array[0] = guardado;
        for (int por : array) {
            System.out.print(por + " ");
        }
        int[] copia = new int[10];
        for (int i = 0; i < copia.length; i++) {
            copia[i]=array[i];
        }
        array[1] = copia[0];
        array[0] = copia[1];
        array[2] = copia[3];
        array[3] = copia[2];
        array[4] = copia[5];
        array[5] = copia[4];
        array[6] = copia[7];
        array[7] = copia[6];
        array[8] = copia[9];
        array[9] = copia[8];
        System.out.print("\n");
        for (int s = 0; s < array.length ; s++) {
            System.out.print(array[s] + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < copia.length; i++) {
            copia[i]=array[i];
        }
        int n = 0;
        int m = (array.length-1);
        do {
            array[n]=copia[m];
            System.out.print(array[n]+" ");
            n++;
            m--;


        }while (n!=array.length && m != -1);
        int par = 0;
        int impar = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2==0){
                par = array[i];
                break;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2!=0){
                impar = array[i];
            }
        }
        System.out.print("\n");
        System.out.printf("El primer par es %d y el ultimo impar es %d",par,impar);
    }
}


