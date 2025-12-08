package FOR;

import java.util.Scanner;

public class Ejercicio17For {

        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce un numero para realizar su factorial: ");
        int numero= lectorTeclado.nextInt();
        int factorial=1;
        if (numero<1){
            System.out.println("No puedo calcular su factorial ");
        }else {

            for (int i = 1; i <= numero; i++) {

                factorial = factorial * i;
            }
            System.out.printf("El factorial de %d es %d", numero,factorial);
        }

    }
}
