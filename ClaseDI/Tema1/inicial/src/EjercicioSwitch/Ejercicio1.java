package EjercicioSwitch;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Ingresa un caracter");
        char caracter=scanner.next().toLowerCase().charAt(0);
        switch (caracter){
            case 'a':
                System.out.println("Vocal");
                break;
            case 'e':
                System.out.println("Vocal");
                break;
            case 'i':
                System.out.println("Vocal");
                break;
            case 'o':
                System.out.println("Vocal");
                break;
            case 'u':
                System.out.println("Vocal");
                break;
            default:
                System.out.println(Character.isLetter(caracter)+"Consonante");
        }
    }
}
