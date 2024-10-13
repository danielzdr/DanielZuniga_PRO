import java.util.Scanner;

public class Ejercicio1For {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce las temperatura: ");
        int nTemperaturas= lectorTeclado.nextInt();
        int temperatura=0;
        int sumatorio=0;
        if (nTemperaturas<=0){
            nTemperaturas=10;
        }


        for (int i=0;i<nTemperaturas;i++){

            System.out.println("Introduce temperatura ");
            temperatura= lectorTeclado.nextInt();
            sumatorio =sumatorio+temperatura;
        }

        System.out.println("La suma de todas las temperaturas es "+sumatorio);
        System.out.println("La media de todas las sumas es "+(float)sumatorio/nTemperaturas);

    }
}
