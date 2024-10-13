package SWITCH;

import java.util.Scanner;

public class ejercicioSwitch2 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String []args){

        System.out.println("Ingrese un caracter: ");
        char caracter= lectorTeclado.next().charAt(0);

        switch (caracter){

            case 'A'|'E'|'I'|'O'|'U':
                System.out.println("Vocal");
                break;
            default:
                System.out.println("Consonante");


        }

    };

}
