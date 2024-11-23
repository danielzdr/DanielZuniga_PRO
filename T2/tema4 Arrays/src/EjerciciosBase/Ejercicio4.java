package EjerciciosBase;

import java.util.Random;

public class Ejercicio4 {

    public static void main(String[] args) {
        //4. Define tres arrays de 20 números enteros cada una, con nombres numero, cuadrado y cubo. Carga el array numero con valores aleatorios entre 0 y 100.
        //    - En el array cuadrado se deben almacenar los cuadrados de los valores que hay en el array numero.
        //    - En el array cubo se deben almacenar los cubos de los valores que hay en numero.

        int [] numero= new int[20];
        int [] cuadrado= new int[20];
        int [] cubo= new int[20];

        Random random =new Random();

        for (int i = 0; i < numero.length; i++) {
            numero[i] = random.nextInt(100)+1;
            System.out.println("El numero es "+numero[i]);
            cuadrado[i]=(int)Math.pow(numero[i],2);
            cubo[i] = (int)Math.pow(numero[i],3 );

        }

        System.out.println("Indice\tNumero\tCuadrado\tCubo");
        System.out.println("------\t------\t--------\t----");
        for (int i = 0; i < numero.length; i++) {
            System.out.printf("%d\t\t%d\t\t%d\t\t%d%n",i,numero[i],cuadrado[i],cubo[i]);
        }



    }
}
