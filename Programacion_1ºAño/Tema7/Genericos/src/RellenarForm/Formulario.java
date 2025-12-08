package RellenarForm;

import RellenarForm.utils.LongitudDNINoValidaException;
import RellenarForm.utils.UltimoDigitoNoLetraException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Formulario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionarFormulario gestionarFormulario= new GestionarFormulario();
        String nombre="";
        String apellidos="";
        String dniPoner="";
        int opcion;
        do {
            System.out.println("1. Rellenar nombre ");
            System.out.println("2. Rellenar apellidos ");
            System.out.println("3. Rellenar dni ");
            System.out.println("4. Finalizar ");
            System.out.println("Introduce la opcion: ");
            opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nombre para rellenar formulario");
                     nombre= scanner.next();
                    break;
                case 2:
                    try {
                        System.out.println("Introduce los apellidos");
                        apellidos= scanner.next();
                    }catch (InputMismatchException e){
                        System.out.println("Error de calculo");
                    }finally {
                        System.out.println("Error de acabado");
                    }

                    break;
                case 3:
                    System.out.println("Introduce el dni ");
                     dniPoner= scanner.next();
                    try{
                        gestionarFormulario.validarDNI(dniPoner);
                        System.out.println("El dni no es correcto");
                    }catch (LongitudDNINoValidaException | UltimoDigitoNoLetraException e){
                    System.out.println("Error de entrada/ salida "+e.getMessage());
                     }
                    break;
                case 4:
                    if ((nombre != null) && (apellidos != null) && (dniPoner != null)){
                        System.out.println("Formulario completado: ");
                        System.out.println("nombre " +nombre);
                        System.out.println("apellidos " +apellidos);
                        System.out.println("dni " +dniPoner);
                        System.out.println("Formulario enviado correctamente");
                    }
                    break;
                default:
                    System.out.println("Opcion invalida.. Intetalo de nuevo");
                    break;
            }
        }while (opcion!=6);
    }
    }

