package FOR;

import java.util.Scanner;

public class Ejercicio8For {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        int acumuladorPositivos=0;
        int acumuladorNegativos=0;
        int numerosPositivos=0;
        int numerosNegativos=0;
        int numerosCeros=0;

        for (int i = 0; i < 10; i++) {


        System.out.println("Pide 10 numeros:");
        int numeros= lectorTeclado.nextInt();


        if (numeros>0){

            acumuladorPositivos+=numeros;
        }else if (numeros<0){

          acumuladorNegativos+=numeros;
        }else{
            numerosCeros++;
        }
        }

        System.out.println("La media de positivos es "+((float)acumuladorPositivos/numerosPositivos));
        System.out.println("La media de negativos es "+((float)acumuladorNegativos/numerosNegativos));
        System.out.println("El numero de 0s es "+numerosCeros);



    }

}
