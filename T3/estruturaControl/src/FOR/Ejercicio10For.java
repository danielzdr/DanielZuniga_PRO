package FOR;

import java.util.Scanner;

public class Ejercicio10For {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        int numeroSS=0, numeroAP=0, numeroCD =0;

        for (int i = 0; i < 10; i++) {

            System.out.println("Introduce una nota: ");
            int nota= lectorTeclado.nextInt();

            if(nota>=0 && nota<=10) {
                if (nota > 4) {
                    numeroAP++;
                } else if (nota < 4) {
                    numeroSS++;
                }
            }
            else {
                System.out.println("Nota invalida");
            }

        }

        System.out.println("El numero de aprobados es "+numeroAP);
        System.out.println("El numero de suspensos es "+numeroSS);
        System.out.println("El numero de CD  es "+numeroCD);
    }
}
