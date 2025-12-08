package EjerciciosBase;

import java.util.Scanner;

public class Ejercicio5 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        //Realiza un programa que pida 8 números enteros, los guarde en un array
        // y que luego muestre esos números junto con la palabra “par” o “impar” según proceda.
        int [] numeros=new int[8];
        System.out.println("Pide los 8 numeros: ");
        for (int i = 0; i < numeros.length; i++) {

            numeros[i] = lectorTeclado.nextInt();
            if (numeros[i] %2==0){
                System.out.println(numeros[i] + "\tpar ");
            } else if (numeros[i] %2!=0) {
                System.out.println(numeros[i]+ "\timpar ");
            }

        }
        }
    }

