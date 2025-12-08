import utils.LongitudDNINoValidaException;
import utils.UltimoDigitoNoLetraException;

import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {

        Formulario formulario = new Formulario();
        Scanner scanner= new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("1. Rellenar nombre");
            System.out.println("2. Rellenar apellidos");
            System.out.println("3. Rellenar dni ");
            System.out.println("4. Finalizar " );
            System.out.println("Introduce la opcion siguente ");
            opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Introduce el dni");
                    String dni= scanner.next();
                    try{
                        formulario.setDni(dni);
                    } catch (UltimoDigitoNoLetraException | LongitudDNINoValidaException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Terminando la ejecucion");
                    break;
                case 4:
                    formulario.mostrarDatos();
                    break;
                default:
                    System.out.println("Opcion invalida, intentalo de nuevo");
                    break;
            }
        }while(opcion!=5);
    }
}
