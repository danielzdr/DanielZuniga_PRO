package Ejercicio1Hotel;

import Ejercicio1Hotel.controller.ReservaHotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class Entrada {
    private static final int TOTAL_HABITACIONES = 20;
    private static ArrayList<ReservaHotel> reservas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Reservas de Hotel ---");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas actuales");
            System.out.println("4. Mostrar reservas ordenadas por nombre");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarReserva(scanner);
                    break;
                case 2:
                    cancelarReserva(scanner);
                    break;
                case 3:
                    mostrarReservas();
                    break;
                case 4:
                    mostrarReservasOrdenadas();
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

    private static void registrarReserva(Scanner scanner) {
        if (reservas.size() >= TOTAL_HABITACIONES) {
            System.out.println("No hay habitaciones disponibles.");
            return;
        }

        System.out.print("Ingrese el número de habitación (1-20): ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine();

        if (numeroHabitacion < 1 || numeroHabitacion > TOTAL_HABITACIONES) {
            System.out.println("Número de habitación inválido.");
            return;
        }

        for (ReservaHotel r : reservas) {
            if (r.getNumeroHabitacion() == numeroHabitacion) {
                System.out.println("La habitación ya está reservada.");
                return;
            }
        }

        System.out.print("Ingrese el nombre del huésped: ");
        String nombreHuesped = scanner.nextLine();

        System.out.print("Ingrese el teléfono del huésped: ");
        String telefonoHuesped = scanner.nextLine();

        System.out.print("Ingrese el DNI del huésped: ");
        String dniHuesped = scanner.nextLine();

        for (ReservaHotel r : reservas) {
            if (r.getDniHuesped().equals(dniHuesped)) {
                System.out.println("El huésped ya tiene una reserva activa.");
                return;
            }
        }

        System.out.print("Ingrese el número de personas: ");
        int numeroPersonas = scanner.nextInt();
        scanner.nextLine();

        ReservaHotel nuevaReserva = new ReservaHotel(numeroHabitacion, nombreHuesped, telefonoHuesped, dniHuesped, numeroPersonas);
        reservas.add(nuevaReserva);
        System.out.println("Reserva registrada con éxito.");
    }

    private static void cancelarReserva(Scanner scanner) {
        System.out.print("Ingrese el DNI del huésped para cancelar la reserva: ");
        String dni = scanner.nextLine();

        boolean encontrada = false;
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getDniHuesped().equals(dni)) {
                reservas.remove(i);
                encontrada = true;
                System.out.println("Reserva cancelada con éxito.");
                break;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontró ninguna reserva con ese DNI.");
        }
    }

    private static void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas actuales.");
        } else {
            System.out.println("\n--- Reservas actuales ---");
            for (ReservaHotel r : reservas) {
                System.out.println(r);
            }
        }
    }

    private static void mostrarReservasOrdenadas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas actuales.");
        } else {
            ArrayList<ReservaHotel> reservasOrdenadas = new ArrayList<>(reservas);
            reservasOrdenadas.sort(Comparator.comparing(ReservaHotel::getNombreHuesped));

            System.out.println("\n--- Reservas ordenadas por nombre ---");
            for (ReservaHotel r : reservasOrdenadas) {
                System.out.println(r);
            }
        }
    }
}
