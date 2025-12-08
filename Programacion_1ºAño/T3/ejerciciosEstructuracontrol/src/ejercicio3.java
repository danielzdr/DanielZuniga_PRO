import java.util.Scanner;

public class ejercicio3 {

    static Scanner lectorTeclado= new Scanner(System.in);
    static public void main (String[]args){

        System.out.println("Pedir un numero: ");
        int a= lectorTeclado.nextInt();

        if (a<0){
            System.out.println("Numero negativo");
        }
          else if (a<10){
            System.out.println("Un digito ");
        }
          else if (a<99){
            System.out.println("Dos digitos ");
        }
          else if (a>99){
            System.out.println("Tres digitos ");
        }
          else {
            System.out.println(a);
        }
        }


    }


