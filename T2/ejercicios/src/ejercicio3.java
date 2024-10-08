import java.util.Scanner;

public class ejercicio3 {

    public static void main (String [] args){
        Scanner lectorTeclado = new Scanner(System.in);
        int operador1= 24;
        int operador2= 7;
        System.out.println("Escribe un numero: ");
        operador1= lectorTeclado.nextInt();
        System.out.println("Escribe otro numero: ");
        operador2= lectorTeclado.nextInt();
        float resultado= operador1+operador2;
        System.out.println("El resultado de la suma es "+resultado);


        resultado= operador1-operador2;
        System.out.println("El resultado de la resta es "+resultado);

        resultado= operador1*operador2;
        System.out.println("El resultado de la multiplicacion es " +resultado);

        resultado = operador1/operador2;
        System.out.println("El resultado de division es "+resultado);

        resultado= operador1%operador2;
        System.out.println("El resultado del resto es " +resultado);

        resultado =(float) operador1/operador2;
        System.out.println("El resultado de division real es "+resultado);

        resultado =(float) operador1%operador2;
        System.out.println("El resultado del resto real es "+resultado);
    }
}
