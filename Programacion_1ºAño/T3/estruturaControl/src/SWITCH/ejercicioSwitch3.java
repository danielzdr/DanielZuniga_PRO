package SWITCH;

import java.util.Scanner;

public class ejercicioSwitch3 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[]args){

        System.out.println("Introduce un numero: ");
        int numero= lectorTeclado.nextInt();
           int resto=numero%2;
        switch (resto) {
            case 0:
                System.out.println("El resultado es par");
                break;

            default:
                System.out.println("El resultado es impar");
        }

    };
}
