import java.util.Scanner;

public class ejercicio2 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main (String []args){


        System.out.println("Dame un numero: ");
        int a= lectorTeclado.nextInt();


        if (a %2 ==0){
          a++;
        }

        else if (a<10){

            a--;
        }

        else if (a<100){

            a*=2;
            a++;
        }
        else {
            //es par no es necesario preguntarlo
            // es mayor a 100

            a=0;
        }

        System.out.println("El valor de a es: "+a);

    }
}
