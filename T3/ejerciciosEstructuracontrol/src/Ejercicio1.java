import java.util.Scanner;

public class Ejercicio1 {

    static Scanner lectorTelado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Pedir un numero: ");
        int numero= lectorTelado.nextInt();
        String s= null;

        if ( numero % 2 == 0) {

            s="par";

        }

        System.out.println(s);
    }


}