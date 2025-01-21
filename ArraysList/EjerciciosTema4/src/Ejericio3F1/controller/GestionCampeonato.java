package Ejericio3F1.controller;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class GestionCampeonato {
    private static final int MAX_PILOTOS = 10;
    private static final int MAX_CARRERAS = 7;
    private static Map<String, Piloto> pilotos = new Hashtable<>();
    private static int carrerasRealizadas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión del Campeonato de Fórmula 1 ---");
            System.out.println("1. Agregar piloto");
            System.out.println("2. Actualizar puntos");
            System.out.println("3. Mostrar información de un piloto");
            System.out.println("4. Mostrar clasificación");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarPiloto(scanner);
                    break;
                case 2:
                    actualizarPuntos();
                    break;
                case 3:
                    mostrarInformacionPiloto(scanner);
                    break;
                case 4:
                    mostrarClasificacion();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void agregarPiloto(Scanner scanner) {
        if (pilotos.size() >= MAX_PILOTOS) {
            System.out.println("No se pueden agregar más pilotos. Límite alcanzado.");
            return;
        }

        System.out.print("Ingrese el nombre del piloto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el bastidor del coche: ");
        String bastidor = scanner.nextLine();

        if (pilotos.containsKey(bastidor)) {
            System.out.println("Ya existe un piloto con este bastidor.");
            return;
        }

        Piloto nuevoPiloto = new Piloto(nombre, bastidor);
        pilotos.put(bastidor, nuevoPiloto);
        System.out.println("Piloto agregado con éxito.");
    }

    public static Map<String, Piloto> getPilotos() {
        return pilotos;
    }

    public static int getCarrerasRealizadas() {
        return carrerasRealizadas;
    }

    private static void actualizarPuntos() {
        if (carrerasRealizadas >= MAX_CARRERAS) {
            System.out.println("No se pueden repartir más puntos. Número máximo de carreras alcanzado.");
            return;
        }

        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados en el campeonato.");
            return;
        }

        Random random = new Random();
        List<Integer> puntosDisponibles = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            puntosDisponibles.add(i);
        }
        Collections.shuffle(puntosDisponibles);

        System.out.println("\n--- Actualización de Puntos ---");
        int index = 0;
        for (Piloto piloto : pilotos.values()) {
            if (index < puntosDisponibles.size()) {
                int puntos = puntosDisponibles.get(index);
                piloto.sumarPuntos(puntos);
                System.out.println(piloto.getNombre() + " suma " + puntos + " puntos.");
                index++;
            }
        }

        carrerasRealizadas++;
        System.out.println("Carrera completada. Total carreras realizadas: " + carrerasRealizadas);
    }

    private static void mostrarInformacionPiloto(Scanner scanner) {
        System.out.print("Ingrese el bastidor del piloto: ");
        String bastidor = scanner.nextLine();

        Piloto piloto = pilotos.get(bastidor);
        if (piloto == null) {
            System.out.println("No se encontró un piloto con ese bastidor.");
        } else {
            System.out.println("\n--- Información del Piloto ---");
            System.out.println(piloto);
        }
    }

    public static void setPilotos(Map<String, Piloto> pilotos) {
        GestionCampeonato.pilotos = pilotos;
    }

    public static void setCarrerasRealizadas(int carrerasRealizadas) {
        GestionCampeonato.carrerasRealizadas = carrerasRealizadas;
    }

    private static void mostrarClasificacion() {
        if (pilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados en el campeonato.");
        } else {
            List<Piloto> clasificacion = new ArrayList<>(pilotos.values());
            clasificacion.sort((p1, p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));

            System.out.println("\n--- Clasificación del Campeonato ---");
            for (Piloto piloto : clasificacion) {
                System.out.println(piloto);
            }
        }
    }
}

