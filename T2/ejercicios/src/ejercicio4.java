import java.util.Scanner;

public class ejercicio4 {

    public static void main (String []args){

        Scanner lectorTeclado = new Scanner(System.in);

        int num1;
        float coste1= 1.25f;

        System.out.printf("Numero de bebidas es:" );
                num1= lectorTeclado.nextInt();

        int num2;
        float coste2= 2.05f;

        System.out.println("Numero de bocadillos es:");
        num2= lectorTeclado.nextInt();

        float resultado1= num1*coste1;
        System.out.println("El coste de las bebidas es "+resultado1);

        float resultado2= num2*coste2;
        System.out.println("El coste de los bocadillos es "+resultado2);

        float resultado3= resultado1+resultado2;
        System.out.println("El coste de la consumicion es "+resultado3);





    };

}
