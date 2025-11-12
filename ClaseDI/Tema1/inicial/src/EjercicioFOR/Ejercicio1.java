package EjercicioFOR;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un numero");
        int numero= scanner.nextInt();
        if (numero<=0){
            numero=10;
            double suma=0;
            for (int i = 0; i < numero; i++) {
                suma+= scanner.nextDouble();
                System.out.printf("Media: %.2f\n", suma/numero);
            }
        }
    }
}
