import java.util.Scanner;

public class Ejercicio1 {

        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[]args){

        System.out.println("Introduce el Nombre: ");
        String nombre=lectorTeclado.next();

        System.out.println("Introduce el apellido: ");
        String apellido=lectorTeclado.next();

        System.out.println("Introduce el dinero disponible: ");
        int dinero= lectorTeclado.nextInt();

        System.out.println("Cuanto cuesta la play5 (sin Iva): ");
        double precioPlay= lectorTeclado.nextFloat();

        System.out.println("Cuanto cuesta el iphone 15 (sin Iva): ");
        double precioIphone= lectorTeclado.nextFloat();

        System.out.println("El Iva actual: ");
        int iva= lectorTeclado.nextInt();

        precioPlay = precioPlay+(precioPlay *((double) iva/100));
        precioIphone = precioIphone+(precioIphone *((double) iva/100));

        boolean condicion1=(precioPlay<=dinero);
        System.out.println("Te puedes comprar la play: "+condicion1);

        boolean condicion2=(precioIphone<=dinero);
        System.out.println("Puedes comprar el iphone 15 "+condicion2);

        boolean condicion3= ((precioPlay<=dinero)&&(precioIphone<=dinero));
        System.out.println("Puedes comprarte las dos cosas "+condicion3);

    };
}
