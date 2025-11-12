package Ejerciciosif;
import java.util.Scanner;
public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un numero entero");
        int numEntero= scanner.nextInt();
        boolean multiplo= numEntero %20==0;
        boolean rango= numEntero>-100 && numEntero<100;
        if (multiplo && rango){
            System.out.println("Es multiplo de 20 y esta entre -100 y 100");
        } else if (multiplo) {
            System.out.println("Es multiplo de 20 pero no esta entre -100 y 100");
        } else if (rango) {
            System.out.println("No es multiplo de 20 y esta entre -100 y 100");
        }else {
            System.out.println("No es ni multiplo ni esta entre -100 y 100");
        }

    }
}
