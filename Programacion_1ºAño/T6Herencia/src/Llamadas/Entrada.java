package Llamadas;

import Llamadas.controller.Centralita;
import Llamadas.model.Llamada;
import Llamadas.model.LlamadaProvincial;
import Llamadas.model.LlamadasNacionales;
import Llamadas.model.LlamadasLocales;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Centralita centralita = new Centralita();

        int opcion;
        do {
            System.out.println("1. Registrar Llamada Local");
            System.out.println("2. Registrar Llamada Provincial");
            System.out.println("3. Registrar Llamada Nacional");
            System.out.println("4. Mostrar Llamadas");
            System.out.println("5. Mostrar Coste Total");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarLlamadaLocal(scanner, centralita);
                    break;
                case 2:
                    registrarLlamadaProvincial(scanner, centralita);
                    break;
                case 3:
                    registrarLlamadaNacional(scanner, centralita);
                    break;
                case 4:
                    centralita.mostrarLlamadas();
                    break;
                case 5:
                    centralita.mostrarCosteTotal();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }while (opcion!=7);
    }


    private static void registrarLlamadaLocal(Scanner scanner, Centralita centralita) {
        System.out.print("Número origen: ");
        String nOrigen = scanner.next();
        System.out.print("Número destino: ");
        String nDestino = scanner.next();
        System.out.print("Duración (segundos): ");
        int duracion = scanner.nextInt();
        Llamada llamada = new LlamadasLocales(nOrigen, nDestino, duracion);
        centralita.registrarLlamada(llamada);
    }

    private static void registrarLlamadaProvincial(Scanner scanner, Centralita centralita) {
        System.out.print("Número origen: ");
        String nOrigen = scanner.next();
        System.out.print("Número destino: ");
        String nDestino = scanner.next();
        System.out.print("Duración (segundos): ");
        int duracion = scanner.nextInt();
        Llamada llamada = new LlamadaProvincial(nOrigen, nDestino, duracion);
        centralita.registrarLlamada(llamada);
    }

    private static void registrarLlamadaNacional(Scanner scanner, Centralita centralita) {
        System.out.print("Número origen: ");
        String nOrigen = scanner.next();
        System.out.print("Número destino: ");
        String nDestino = scanner.next();
        System.out.print("Duración (segundos): ");
        int duracion = scanner.nextInt();
        System.out.print("Franja (1, 2, o 3): ");
        int franja = scanner.nextInt();
        Llamada llamada = new LlamadasNacionales(nOrigen, nDestino, duracion, franja);
        centralita.registrarLlamada(llamada);
    }
}
