import java.util.Scanner;

public class ejercicio13 {

    public static void main (String[]args){
        Scanner lectorTeclado= new Scanner(System.in);


        System.out.println("Escribe un numero entre 0 y 9: ");
        int num1= lectorTeclado.nextInt();
        System.out.println("Escribe un numero entre 0 y 9: ");
        int num2= lectorTeclado.nextInt();

        boolean condicion1 = (num1 % 2 == 0)&&(num2 % 2 != 0);


        System.out.println("El siguente número es par y el siguente impar: " +condicion1);

        boolean condicion2= (num1 > 2 * num2) && (num1 < 8);
        System.out.println("El primero es superior al doble del segundo e inferior a 8: "+condicion2);

        boolean condicion3=(num1==num2) || (num1-num2)<2;
        System.out.println("Son iguales o la diferencia entre el primero y el segundo es menor que 2: "+condicion3);




    };
}
