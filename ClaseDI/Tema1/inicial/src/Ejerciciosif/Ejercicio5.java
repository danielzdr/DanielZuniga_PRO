package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio5{
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un numero entero");
        int num= scanner.nextInt();
        int resto = num;
        if (num  %2==0){
            resto+=2;
        } else if (num %3==0) {
            resto +=3;
        } else if (num %5==0) {
            resto+=5;
        } else if (resto == num) {
            resto +=1;
        }else {
            System.out.println("Error");
        }

        System.out.println(resto);

    }
}
