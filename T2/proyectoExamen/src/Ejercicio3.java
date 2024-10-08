import java.util.Scanner;

public class Ejercicio3 {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[]args){



        System.out.printf("Tipo de cafe que prefiere:" );
        String cafe= lectorTeclado.next();

        System.out.println("Introduce la cantidad de azucar: ");
        int azucar= lectorTeclado.nextInt();

        System.out.println("Introduce el tamaño del cafe: ");
        String tamano= lectorTeclado.next();

        System.out.printf("Preparando un %s de tamaño %s con %d cucharaditas de azucar",cafe,tamano,azucar);




    };

}
