import java.util.Scanner;

public class ejercicio10 {
    public static void main(String[]args){

        Scanner lectorTeclado= new Scanner(System.in);

        int decenasMil,unidadesMil,numero,centenas,decenas,unidades;

        System.out.println("Decenas de mil: ");
        decenasMil= lectorTeclado.nextInt();

        System.out.println("Unidades de mil: ");
        unidadesMil= lectorTeclado.nextInt();

        System.out.println("Centenas: ");
        centenas= lectorTeclado.nextInt();

        System.out.println("Decenas: ");
        decenas= lectorTeclado.nextInt();

        System.out.println("Unidades: ");
        unidades= lectorTeclado.nextInt();

       numero= decenasMil * 1000 +unidadesMil* 1000 + centenas * 100 + decenas * 10 + unidades;
        System.out.println("Numero introducido: "+numero);







    };


}
