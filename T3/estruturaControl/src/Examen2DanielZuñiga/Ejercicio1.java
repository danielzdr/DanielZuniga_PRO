package Examen2DanielZuñiga;

import java.util.Scanner;

public class Ejercicio1 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce el primer numero: ");
        int operando1= lectorTeclado.nextInt();

        System.out.println("Introduce el segundo numero: ");
        int operando2= lectorTeclado.nextInt();


        int opcion=0;
        if (operando1>0 && operando2 >0){
            System.out.println("Si se puede realizar operaciones con numero positivos ");
            do {
                System.out.println("1. Suma ");
                System.out.println("2. Resta ");
                System.out.println("3. Multiplicacion ");
                System.out.println("4. Division ");
                System.out.println("5. Salir");

                System.out.println("Introduce una opcion ");
                 opcion= lectorTeclado.nextInt();

                 switch (opcion){
                     case 1:
                         System.out.println("la suma de los dos numeros es " +(operando1+operando2));
                         break;
                     case 2:
                         System.out.println("La resta de los dos numeros es "+(operando1-operando2));
                        break;
                     case 3:
                         System.out.println("La multiplicacion de los dos numeros es "+(operando1*operando2));
                         break;
                     case 4:
                         System.out.println("La division de los dos numeros es "+((float)operando1/operando2));
                         break;
                     case 5:
                         System.out.println("Saliendo..... ");
                         break;
                     default:
                         System.out.println("Opcion no valida, intentalo de nuevo... ");
                         break;
                 }

            }while(opcion!=6);

        } else if (operando1<0 && operando2<0) {
            System.out.println("No se puede realizar operciones de numero negativos ");
        }



    }
}
