package Ejercicio1Ejercicios;

import Ejercicio1Ejercicios.controller.hotel;

import java.util.Scanner;


public class Entrada {

        private int numeroHabitacion;
        private String nombreHuesped;
        private String telefonoHuesped;
        private String dniHuesped;
        private int numeroPersonas;


        public Entrada(int numeroHabitacion, String nombreHuesped, String telefonoHuesped, String dniHuesped, int numeroPersonas) {
            this.numeroHabitacion = numeroHabitacion;
            this.nombreHuesped = nombreHuesped;
            this.telefonoHuesped = telefonoHuesped;
            this.dniHuesped = dniHuesped;
            this.numeroPersonas = numeroPersonas;
        }


        public int getNumeroHabitacion() {
            return numeroHabitacion;
        }

        public String getNombreHuesped() {
            return nombreHuesped;
        }

        public String getDniHuesped() {
            return dniHuesped;
        }

        public void mostrarReservasActuales() {
            System.out.println("Habitación: " + numeroHabitacion +
                    ", Huésped: " + nombreHuesped +
                    ", Teléfono: " + telefonoHuesped +
                    ", DNI: " + dniHuesped +
                    ", Número de personas: " + numeroPersonas);
        }
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        hotel hotel = new hotel();

        while (true) {
            System.out.println("\n--- Gestión de Reservas de Hotel ---");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reservas actuales");
            System.out.println("4. Mostrar reservas ordenadas");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Número de habitación: ");
                    int habitacion = scanner.nextInt();


                    System.out.print("Nombre del huésped: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Teléfono del huésped: ");
                    String telefono = scanner.nextLine();

                    System.out.print("DNI del huésped: ");
                    String dni = scanner.nextLine();

                    System.out.print("Número de personas: ");
                    int numPersonas = scanner.nextInt();

                    Entrada nuevaReserva = new Entrada(habitacion, nombre, telefono, dni, numPersonas);
                    hotel.registrarReserva(nuevaReserva);
                    break;

                case 2:
                    System.out.print("Ingrese el DNI de la reserva a cancelar: ");
                    String dniCancelar = scanner.nextLine();
                    hotel.cancelarReserva(dniCancelar);
                    break;

                case 3:
                    hotel.mostrarReservasActuales();
                    break;

                case 4:
                    hotel.mostrarReservasOrdenadas();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
    }



