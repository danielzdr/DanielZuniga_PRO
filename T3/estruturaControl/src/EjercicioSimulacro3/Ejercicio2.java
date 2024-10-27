package EjercicioSimulacro3;

import java.util.Scanner;

public class Ejercicio2 {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        int totalNotas = 0;
        int sumaNotas = 0;


        int contador = 1;
        for (int i = 0; i < 20; i++) {


            System.out.print("Introduce la nota del alumno " + contador + ": ");
            int nota = lectorTeclado.nextInt();
            sumaNotas+=nota;

            // Verifica si la nota es un suspenso (<5)
            if (nota < 5) {
                totalNotas++;
            }


        }


        // Imprime el resultado
        System.out.println("¿Hay algún suspenso?: " +totalNotas);
        System.out.println("Nota media de la clase: " + sumaNotas+totalNotas);
    }
}
