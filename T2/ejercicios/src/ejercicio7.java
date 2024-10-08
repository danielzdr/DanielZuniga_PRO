import java.util.Scanner;

public class ejercicio7 {
    static Scanner lectorTeclado = new Scanner(System.in);
    public static void main(String[]args){

        System.out.println("Escribe un radio entero: ");
        int valorRadio=lectorTeclado.nextInt();
        double longitud = 2*Math.PI*valorRadio;


        System.out.println("Longitud de la circunferencia: "+longitud);
        double area= Math.PI*Math.pow(valorRadio,2);
        System.out.println("Area del circulo: "+area);

    };
}
