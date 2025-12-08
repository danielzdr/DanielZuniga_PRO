package Examen2DanielZuñiga;

import java.util.Scanner;

public class Ejercicio2 {
    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.print("Introduce un número aleatorio entre 1 y 100: ");
        int numeroUsuario = lectorTeclado.nextInt();
        int numeroMax = 0, numeroMin = 0, sumatorioGenerados = 0;
        double media = 0.0;
        boolean premio = false;
        for (int i = 1; i < 10; i++) {
            int numeroAleatorio = (int) (Math.random() * 100)+1;
            if (numeroAleatorio>numeroMax){
                numeroMax=numeroAleatorio;
            }
            if (numeroAleatorio<numeroMin){
                numeroMin=numeroAleatorio;
            }
            if (numeroAleatorio==numeroUsuario){
                premio=true;
            }
            sumatorioGenerados+=numeroAleatorio;
        }

        media=(double) sumatorioGenerados/10;

        System.out.println("El numero mas grande es "+numeroMax);
        System.out.println("El numero mas pequeño es "+numeroMin);
        System.out.println("El sumatorio es "+sumatorioGenerados);
        System.out.println("La media es "+media);

        if (premio){
            System.out.println("Has acertado");
        }else{
            System.out.println("Has fallado");
        }

    }
}
