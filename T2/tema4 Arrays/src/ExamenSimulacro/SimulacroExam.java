package ExamenSimulacro;

import java.util.Scanner;

public class SimulacroExam {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Realizar pedido");
            System.out.println("2. Servir pedido");
            System.out.println("3. Mostrar pedidos pendientes");
            System.out.println("4. Mostrar caja");
            System.out.println("Que opcion quieres elegir ");
            opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion no valida, vuelva a introducir una opcion");
                    break;
            }

        }while(opcion!=5);
    }
}
