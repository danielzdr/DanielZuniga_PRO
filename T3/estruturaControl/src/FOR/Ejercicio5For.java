package FOR;


import java.util.Scanner;

public class Ejercicio5For {
    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ingrese primer numero del 0 al 5: ");
        int base= lectorTeclado.nextInt();

        System.out.println("Ingrese el segundo numero del 0 al 5: ");
        int exponente= lectorTeclado.nextInt();

           if (base==0 && exponente==0){
               System.out.println("Error: 0^0 no esta definido");

           } else{

                   int resultado=1;

               for(int i=0; i<=exponente;i++){
                   resultado*=base;
               }

               System.out.println(base +"^"+exponente+"="+resultado);
           }

    }


}
