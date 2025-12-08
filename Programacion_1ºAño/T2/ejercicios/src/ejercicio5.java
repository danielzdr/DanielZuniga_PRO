import java.util.Scanner;

public class ejercicio5 {
    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main (String[]args){

        System.out.println("Numero de segundos: ");
        int segundos =lectorTeclado.nextInt();
        int horas = segundos/3600;
        int minutos= (3600/60);
        segundos = segundos;

        System.out.println("Horas = "+horas);
        System.out.println("Minutos = "+minutos);
        System.out.println("Segundos = "+segundos);


    };
}
