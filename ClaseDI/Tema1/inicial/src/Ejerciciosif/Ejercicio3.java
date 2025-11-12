package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un numero");
        int num= scanner.nextInt();
        if (num<1000 || num>9999){
            System.out.println("Es un numero de 4 digitos");
        }else {
            System.out.println("El numero no contiende 4 digitos");
        }
            int a = num % 10, b = (num / 10) % 10, c = (num / 100) % 10, d = num / 1000;
            if (a==d && b==c){
                System.out.println("El numero es capicua");
            }else {
                System.out.println("El numero no es capicua");
            }

    }
}
