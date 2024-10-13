import java.util.Scanner;

public class ejericicio9Switch {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Ingrese un numero del 1 al 12: ");
        int numero= lectorTeclado.nextInt();

        switch (numero){

            case 1,2,3:
                System.out.println("invierno");
                break;

            case 4,5,6:
                System.out.println("primavera");
                break;

            case 7,8,9:
                System.out.println("verano");
                break;

            case 10,11,12:
                System.out.println("otoño");
                break;

        }

    }
}
