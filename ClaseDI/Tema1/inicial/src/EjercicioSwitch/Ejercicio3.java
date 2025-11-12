package EjercicioSwitch;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Introduce un operador");
        char operador= scanner.next().charAt(0);
        System.out.println("Introduce el primer numero");
        int num1= scanner.nextInt();
        System.out.println("Introduce el segundo numero");
        int num2= scanner.nextInt();
        switch (operador){
            case '+':
                System.out.println(num1+num2);
                break;
            case  '-':
                System.out.println(num1-num2);
                break;
            case '*':
                System.out.println(num1*num2);
                break;
            case '/':
                System.out.println(num1/num2);
                break;
            default:
                System.out.println("Error numero invalido");
        }
    }
}
