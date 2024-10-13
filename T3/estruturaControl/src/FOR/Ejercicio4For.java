package FOR;

import java.util.Scanner;

public class Ejercicio4For {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Dime cual es el numero inicial: ");
        int numeroInicial= lectorTeclado.nextInt();

        System.out.println("Dime cual es el numero final: ");
        int numeroFinal= lectorTeclado.nextInt();

        if (numeroInicial>=numeroFinal) {
            System.out.println("No se puede proceder a la ejecucion");
        }    else {


            for (int i = numeroInicial; i <= numeroFinal; i++) {

                System.out.println("Tabla de multiplicar del: " + i);

                for (int j = 0; j < 11; j++) {

                    System.out.printf("\t%d * %d = %d\n",i,j,i*j);
                }
            }
        }
    }
}
