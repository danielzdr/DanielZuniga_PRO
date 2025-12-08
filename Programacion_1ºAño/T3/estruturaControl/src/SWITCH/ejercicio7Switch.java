package SWITCH;

import java.util.Scanner;

public class ejercicio7Switch {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce una nota: ");
        int nota= lectorTeclado.nextInt();

        switch (nota){
            case 1,4:
                System.out.println("Suspenso");
                break;

            case 5:
                System.out.println("Suficiente");
                break;

            case 6:
                System.out.println("Bien");
                break;

            case 7,8:
                System.out.println("Notable");
                break;

            case 9,10:
                System.out.println("Sobresaliente");
                break;

        }
    }
}
