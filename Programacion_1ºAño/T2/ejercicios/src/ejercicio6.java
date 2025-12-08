import java.util.Scanner;

public class ejercicio6 {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main (String[]args){
        double valor_compra;
        System.out.println("El valor de la compra(entre 0.00 y 500.00): ");
        valor_compra =lectorTeclado.nextDouble();

        int IVA;

        System.out.println("IVA(entre 0 y 25%): ");
        IVA= lectorTeclado.nextInt();

        double compra = valor_compra-(valor_compra/100);
        double iva_resto= valor_compra-compra;

        System.out.println("Compra: "+compra);
        System.out.println("IVA: "+iva_resto);

        System.out.println("========");
        System.out.printf("%.2f\n",valor_compra);


    };


}
