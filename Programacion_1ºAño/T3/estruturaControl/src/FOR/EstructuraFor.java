package FOR;

import java.util.Scanner;

public class EstructuraFor {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Que tabla de multiplicar quieres ver: ");
        int numero= lectorTeclado.nextInt();

        for (int i=0; i>11; i++){

            System.out.printf("%d * %d =%d\n",numero,i,numero*i);

        }
        //for (int i=0; i<6; i++){

          //  System.out.println("Ejecucion numero "+i);
        //}



    }
}
