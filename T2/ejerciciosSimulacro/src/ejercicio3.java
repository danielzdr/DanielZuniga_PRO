import java.util.Scanner;

public class ejercicio3 {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main (String[]args){


        System.out.printf("Introduce el precio del coche: %d%n");
        int precioCoche=lectorTeclado.nextInt();

        System.out.println("Introduce el plazo de meses: ");
        int plazosMeses= lectorTeclado.nextInt();

        int total= (int)precioCoche/plazosMeses;
        System.out.printf("Vas a pagar el coche de %d en %d, con un total de %d cada plazo(sin tener en cuenta los interes) ",precioCoche,plazosMeses,total);







    };
}
