package ExamenArraysDanielZuñiga;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        Random random=new Random();

    int[] numeros = new int[15];

        for (int i = 0; i < numeros.length; i++) {
            numeros[i]= random.nextInt(16)+1;
            System.out.println(numeros[i]);
        }
        System.out.println();


        for (int i = 0; i < numeros.length; i++) {
            Arrays.sort(numeros);
        }
        System.out.println("Mostrar los numeros ordenados"+numeros);




        for (int i = 0; i < numeros.length+1; i++) {
        numeros[i]=numeros[i+1];
            System.out.println(numeros[i+1]);
            System.out.println();
            System.out.println("Mostrar los elementos en posiciones pares"+numeros[i+1]);
        }



        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i]%2==0){
                System.out.println("El elemento es par"+numeros[i]);
            }else {
                System.out.println("El elemento es impar");
            }
            System.out.println();
        }
        System.out.println(numeros);
    }
}

