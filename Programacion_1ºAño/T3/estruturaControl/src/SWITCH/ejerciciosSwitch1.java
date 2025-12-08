package SWITCH;

import java.util.Scanner;

public class ejerciciosSwitch1 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[]args){

        System.out.println("Que dia de la semana quieres sacar: ");
        int numero= lectorTeclado.nextInt();

        switch (numero){
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miercoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sabado");
                break;
            case 7:
                System.out.println("Domingo");
                break;

        }



    };
}
