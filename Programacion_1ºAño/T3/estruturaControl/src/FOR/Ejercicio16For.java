package FOR;

import java.util.Scanner;

public class Ejercicio16For {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        int aleatorio= (int)(Math.random()*30)+1;
        System.out.println(aleatorio);
        int intentos=0;
        for (int i = 0; i < 10; i++) {
            System.out.println("Por favor dime tu numero ");
            int numeroUsuario= lectorTeclado.nextInt();
            intentos++;
            if(aleatorio==numeroUsuario){
                System.out.println("Enorabuena has acertado ");
                break;
            }else{
                System.out.println("Intentalo de nuevo ");
            }
        }
        System.out.println("Has usado " +intentos);

    }
}
