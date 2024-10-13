import java.util.Scanner;

public class ejercicio6Switch {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce un numero del 1 al 4: ");
        int numero= lectorTeclado.nextInt();


        switch (numero){

            case 1:
                System.out.println("Opcion 1 seleccionada");
                break;

            case 2:
                System.out.println("Opcion 2 seleccionada");
                break;

            case 3:
                System.out.println("Opcion 3 seleccionada");
                break;

            case 4:
                System.out.println("Opcion 4 seleccionada");
                break;

            default:
                System.out.println("Salir");

        }
    }
}
