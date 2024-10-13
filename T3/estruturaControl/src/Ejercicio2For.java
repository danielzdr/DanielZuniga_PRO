import java.util.Scanner;

public class Ejercicio2For {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Todos los numeros: ");
        int numero = lectorTeclado.nextInt();

        for (int i = 0; i < 11; i++) {

            System.out.println("Tablas de multiplicar " +i);

            for(int j = 0; j < 11; j++){
            //tabla de multiplicar de i
                System.out.printf("\t%d * %d = %d\n",i,j,i*j);
            }

        }
    }
}
