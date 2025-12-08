import java.io.PrintStream;
import java.util.Scanner;

public class ejercicio11 {

    public static void main (String[]args){
        Scanner lectorTeclado= new Scanner(System.in);

        int numero = 100;

        System.out.println("Escribe un numero entre 0 y 100: "+numero);
        numero= lectorTeclado.nextInt();

        boolean mayor=numero>50;
        System.out.println("El numero es mayor a 50 :"+mayor);

        boolean par=(numero%2==0);
        System.out.println("El numero es par: "+par);


    }

}
