package FOR;


import java.util.Scanner;

public class Ejercicio5For {
    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ingrese primer numero del 0 al 5: ");
        int base= lectorTeclado.nextInt();

        System.out.println("Ingrese el segundo numero del 0 al 5: ");
        int exponente= lectorTeclado.nextInt();

        int resultado =1;

           if  (exponente==0){
               System.out.println("El resultado de la potencia es 1 ");

           } else if (base==0) {

               System.out.println("No se calculan potenciass con base 0 ");

           } else {
               for (int i = 0; i < exponente; i++) {
                   resultado = resultado *base;
               }

               System.out.println("El resultado de la potencia es " +resultado);
           }



    }


}
