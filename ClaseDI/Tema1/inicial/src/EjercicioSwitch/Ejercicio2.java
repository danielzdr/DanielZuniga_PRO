package EjercicioSwitch;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduce un numero para comprobar si es par o impar");
        int num=scanner.nextInt();
        switch (Math.abs(num)%2){
            case 1:
                System.out.println("Impar");
                break;
            case 2:
                System.out.println("Par");
            default:
                System.out.println("Error numero no valido");
        }
    }
}
