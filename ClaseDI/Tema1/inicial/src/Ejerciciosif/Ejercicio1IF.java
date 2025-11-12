package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio1IF {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un numero");
        int num1 = scanner.nextInt();
        System.out.println("Introduce el segundo numero");
        int num2= scanner.nextInt();

        if (num1>num2){
            System.out.println("El numero a es mayor");
        } else if (num1<num2) {
            System.out.println("El numero b es menor");
        }else {
            System.out.println("Error en todos");
        }

    }

}
