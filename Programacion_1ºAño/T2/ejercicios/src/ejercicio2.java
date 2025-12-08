import java.util.Scanner;

public class ejercicio2 {

    public static void main (String [] args){
        Scanner lectorTeclado= new Scanner(System.in);
        String nombre;
        System.out.println("Escribe tu nombre completo");
        nombre = lectorTeclado.nextLine();

        System.out.println("Escriba tu edad");
        int edad =lectorTeclado.nextInt();

        System.out.printf("Te llamas %s tienes %d ",nombre, edad);





    };

}
