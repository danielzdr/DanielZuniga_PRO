package FOR;

import java.util.Scanner;

public class Ejercicio9For {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        int sueldosMayores = 0, sueldosMenores = 0;
        for (int i = 0; i < 10; i++) {

            System.out.println("Introduce el sueldo: ");
            int sueldo = lectorTeclado.nextInt();

            if (sueldo < 1000) {
                sueldosMayores++;

            }
            sueldosMenores+=sueldosMayores;


        }
    }
}
