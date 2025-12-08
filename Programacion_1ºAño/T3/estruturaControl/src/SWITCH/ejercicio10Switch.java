package SWITCH;

import java.util.Scanner;

public class ejercicio10Switch {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introcude un caracter: ");
        char cararacter=lectorTeclado.next().charAt(0);




        switch (cararacter){

            case 'a':
                System.out.println("Botella y tiene un precio de 10");
                break;

            case 'b':
                System.out.println("Lapiz y tiene un valor de 3");
                break;

            case 'c':
                System.out.println("Ordenador tiene un valor de 4");
                break;

        }
    }

}
