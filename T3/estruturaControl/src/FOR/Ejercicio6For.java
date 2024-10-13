package FOR;

import java.util.Scanner;

public class Ejercicio6For {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce un dado: ");
        int dado1 = lectorTeclado.nextInt();

        System.out.println("Introduce el segundo dado: ");
        int dado2 = lectorTeclado.nextInt();

        if (dado1 > dado2) {
            int temp = dado1;
            dado1 = dado2;
            dado2 = temp;

            System.out.printf("Numeros pares entre %d y %d\n", dado1, dado2);
        }

            for (int i = dado1 + 1; i < dado2; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }


            }

        }

    }




