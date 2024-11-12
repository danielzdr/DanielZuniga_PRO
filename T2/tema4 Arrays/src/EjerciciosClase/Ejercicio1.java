package EjerciciosClase;

import java.util.Scanner;

public class Ejercicio1 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {
        int [] numeros= new int[5];


        int suma =0;
        int media=0;

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Pide los numeros por teclado: ");
            numeros[i]=lectorTeclado.nextInt();
        }

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] *= 2;
            suma+=numeros[i];
        }

        for (int item:numeros){
            System.out.println(item);
        }


        System.out.println("La media de los numeros son: "+((double)suma/ numeros.length));


    }
}
