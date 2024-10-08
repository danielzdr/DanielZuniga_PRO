import java.util.Scanner;

public class ejercicio15 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main (String[]args){



        System.out.println("Entero: ");
        int numero= lectorTeclado.nextInt();


        int num1 = numero +5;
        System.out.println("Incrementar en 5 unidades: "+num1);

        int num2=numero-3;
        System.out.println("Decrementar en 3 unidades: "+num2);

        int num3=numero*10;
        System.out.println("Multiplicar por 10: "+num3);

        int num4=numero/2;
        System.out.println("Dividir entre 2: "+num4);

    };
}
