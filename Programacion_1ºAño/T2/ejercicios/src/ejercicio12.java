import java.util.Scanner;

public class ejercicio12 {

    public static void main (String[]args){

        Scanner lectorTeclado=new Scanner(System.in);

        System.out.println("Escribe una palabra ");
        String pal1=lectorTeclado.next();
        System.out.println("Escribe una palabra ");
        String pal2= lectorTeclado.next();

        boolean resultado= pal1.equals(pal2) ;
        System.out.println("El resultado de la comparacion son iguales: " +resultado);


        boolean menor=pal1.compareTo(pal2)<0;
        System.out.println("La palabra primera es menor a la segunda: "+menor);

        boolean distintas=!resultado;
        System.out.println("Las palabras son distintas: "+distintas);


    };
}
