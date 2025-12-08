import java.util.Scanner;

public class Ejercicio4 {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("Indroduce nombre y apellido: ");
        String nombre= lectorTeclado.next();

        System.out.println("Introduce el sueldo a percibir: ");
        int sueldo= lectorTeclado.nextInt();

        System.out.println("Indroduce la edad: ");
        int edad= lectorTeclado.nextInt();

        System.out.println("Indroduce el dia de tu cumpleaños: ");
        int cumple= lectorTeclado.nextInt();

        System.out.println("Indroduce si tienes o no carnet: ");
        boolean carnet= lectorTeclado.nextBoolean();

        boolean condicion1=edad<50 && sueldo<=40000 && carnet;
        boolean condicion2= edad>45 && sueldo<20000 && cumple%2==0;
        System.out.println("El analisis del canditado es "+(condicion1 || condicion2));



    };
}
