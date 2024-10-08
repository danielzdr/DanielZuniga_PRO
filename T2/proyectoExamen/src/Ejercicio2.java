import java.util.Scanner;

public class Ejercicio2 {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main (String []args){


        int aleatorio=(int)(Math.random()*9000)+1000;
        System.out.println("Introduce un numero entre el 1000 y 9999: ");
        int numero = lectorTeclado.nextInt();

        int unidades=resto %10;

        System.out.println("Coinciden las unidades "+(unidades==unidades));
        int decenas= resto/10;
        System.out.println("Has acertado las decenas "+(decenas==decenas));
        int centenas= resto/100;
        int resto =numero%1000;
        resto= resto%100;
        System.out.println("Has acertado las centenas "+(centenas==centenas));
        int millares=(numero/1000);
        System.out.println("Has acertado los millares "+(millares==millares));





    };

}
