package EjercicioSimulacro3;

import java.util.Scanner;

public class Ejercicio3 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        final int PIN=1111;
        double saldo=0;
        int pinUsuario=0;
        int opcion=0;
        do{
            System.out.println("1.Ingresar dinero: ");
            System.out.println("2.Sacar dinero: ");
            System.out.println("3.Consultar el estado de la cuenta: ");
            System.out.println("4.Salir ");
            System.out.println("Introduce una opcion");
            opcion= lectorTeclado.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Dime el pin de la cuenta: ");
                    pinUsuario= lectorTeclado.nextInt();
                    if (pinUsuario==PIN) {
                        System.out.println("Que cantidad quieres ingresar: ");
                        int cantidad = lectorTeclado.nextInt();
                        if (cantidad > 0) {
                            saldo+=cantidad;
                            System.out.println("Saldo actualizado");
                        } else {
                            System.out.println("Cantidad invalida ");
                        }
                    }else{
                        System.out.println("No es el PIN ");
                        }
                    break;
                case 2:
                    System.out.println("Dime el pin de la cuenta: ");
                    pinUsuario= lectorTeclado.nextInt();
                    if (pinUsuario==PIN){
                        System.out.println("Que cantidad quieres sacar: ");
                        int cantidad= lectorTeclado.nextInt();
                        if(cantidad>0 && saldo>=cantidad){
                            saldo-=cantidad;
                            System.out.println("Saldo actualizado ");
                        }else{
                            System.out.println("Cantidad invalida ");
                        }
                    }else {
                        System.out.println("No es el PIN ");
                    }
                    break;
                case 3:
                    System.out.println("Consultar el saldo de la cuenta "+saldo);
                    break;
                case 4:
                    System.out.println("Saliendo.... ");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(opcion!=4);


    }
}
