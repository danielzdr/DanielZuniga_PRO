import java.util.Scanner;

public class ejercicio8Switch {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Ingrese un caracter: ");
        char operacion=lectorTeclado.next().charAt(0);

        System.out.println("Ingresa el primer numero: ");
        int op1= lectorTeclado.nextInt();

        System.out.println("Ingresa el segundo numero: ");
        int op2= lectorTeclado.nextInt();



        switch (operacion){

            case '+':
                System.out.println("El resultado de la suma es: "+(op1+op2));
                break;

            case '-':
                System.out.println("El resultado de la resta es: "+(op1-op2));
                break;

            case '*':
                System.out.println("El resultado de la multiplicacion es: "+(op1*op2));
                break;

            case '/':
                System.out.println("El resultado de la division es: "+(op1/op2));
                break;
        }

    };
}
