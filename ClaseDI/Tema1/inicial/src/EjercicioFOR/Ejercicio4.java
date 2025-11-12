package EjercicioFOR;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce el valor de a");
        int a= scanner.nextInt();
        System.out.println("Introduce el valor de b");
        int b= scanner.nextInt();
        for (int i = a; i <=b ; i++) {
            System.out.println("Tabla del " + i + ":");
            for (int j = 0; j <=10 ; j++) {
                System.out.println(i + " x " + j + " = "+(i*j));
            }
            System.out.println();
        }
    }
}
