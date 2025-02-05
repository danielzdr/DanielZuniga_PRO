package Ejercicio10Llamadas;

import Ejercicio10Llamadas.model.Centralita;
import Ejercicio10Llamadas.model.LlamadaLocal;
import Ejercicio10Llamadas.model.LlamadaNacional;

import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {

        Centralita centralita= new Centralita();
        LlamadaLocal llamadaLocal= new LlamadaLocal();
        LlamadaNacional llamadaNacional= new LlamadaNacional();
        Scanner scanner= new Scanner(System.in);
        int opcion=0;
        do {

            System.out.println("Introduce una opcion a elegir");
            opcion= scanner.nextInt();
            System.out.println("1. Agregar llamada local");
            System.out.println("2. Agregar llamada nacional");
            System.out.println("3. Mostrar datos de llamadas locales");
            System.out.println("4. Mostrar datos de llamadas nacionales");
            System.out.println("5. Mostrar coste acumulado");
            System.out.println("6. Salir del programa");

            switch (opcion){
                case 1:
                    centralita.agregarLocal(new LlamadaLocal(12233445,423432423,45.5));
                    break;
                case 2:
                    centralita.agregarNacional(new LlamadaNacional(122334453,423432423,45.5, 23.6, 2));
                    break;
                case 3:
                    llamadaLocal.mostrarDatos();
                    break;
                case 4:
                    llamadaNacional.mostrarDatos();
                    break;
                case 5:
                    centralita.mostrarCostes();
                    break;
                case 6:
                    System.out.println("Saliendo del programa....");
                    break;
                default:
                    System.out.println("Accion no valida, intentelo de nuevo");
                    break;
            }
        }while (opcion!=7);
    }
}
