package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un numero");
        int num= scanner.nextInt();
        if (num %2==0){
            num++;
        }else {
            num--;
        }
        System.out.println(num);
    }
}
