package WHILE;


import java.util.Scanner;

public class Ejercicio2While {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {


    // Calcúlese el mínimo común múltiplo de dos números entre 1 y 100.
    // Para obtener el mcm se tomará uno de los valores introducidos y se irá multiplicando por 1, 2, 3, etc,
    // hasta que el valor obtenido sea múltiplo del otro número, que sería el valor buscado. (MinimoComunMultiplo)

    int num1;
    int num2;


        System.out.println("Introduce un numero entre 1 al 100: ");
        num1= lectorTeclado.nextInt();
        System.out.println("Introduce el otro numero entre 1 al 100: ");
        num2= lectorTeclado.nextInt();

        int mcm=num1;

    while(mcm % num1!=0){
        mcm+=num1;
        }

        System.out.printf("El minimo comun  multiplo de %d y el minimo comun multiplo de %d es %d",num1,num2,mcm);
    }
}