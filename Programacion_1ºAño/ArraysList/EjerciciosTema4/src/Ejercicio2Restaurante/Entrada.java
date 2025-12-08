package Ejercicio2Restaurante;

import java.util.Scanner;
import java.util.ArrayList;
public class Entrada {
    private static List<Mesa> mesasOcupadas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Gestión de Mesas del Restaurante ---");
            System.out.println("1. Asignar mesa");
            System.out.println("2. Liberar mesa");
            System.out.println("3. Realizar pedido");
            System.out.println("4. Mostrar asignaciones actuales");
            System.out.println("5. Mostrar mesas ordenadas por precio");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    asignarMesa(scanner);
                    break;
                case 2:
                    liberarMesa(scanner);
                    break;
                case 3:
                    realizarPedido(scanner);
                    break;
                case 4:
                    mostrarAsignaciones();
                    break;
                case 5:
                    mostrarMesasOrdenadasPorPrecio();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo nuevamente.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void asignarMesa(Scanner scanner) {
        if (mesasOcupadas.size() >= TOTAL_MESAS) {
            System.out.println("No hay mesas disponibles.");
            return;
        }

        System.out.print("Ingrese el número de mesa (1-10): ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        if (numeroMesa < 1 || numeroMesa > TOTAL_MESAS) {
            System.out.println("Número de mesa inválido.");
            return;
        }

        for (Mesa m : mesasOcupadas) {
            if (m.getNumeroMesa() == numeroMesa) {
                System.out.println("La mesa ya está ocupada.");
                return;
            }
        }

        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        Mesa nuevaMesa = new Mesa(numeroMesa, nombreCliente);
        mesasOcupadas.add(nuevaMesa);
        System.out.println("Mesa asignada con éxito.");
    }

    private static void liberarMesa(Scanner scanner) {
        System.out.print("Ingrese el número de mesa a liberar: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        boolean encontrada = false;
        for (int i = 0; i < mesasOcupadas.size(); i++) {
            if (mesasOcupadas.get(i).getNumeroMesa() == numeroMesa) {
                Mesa mesa = mesasOcupadas.get(i);
                System.out.println("\n--- Información de la mesa ---");
                System.out.println(mesa);
                mesasOcupadas.remove(i);
                encontrada = true;
                System.out.println("Mesa liberada con éxito.");
                break;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontró ninguna mesa con ese número.");
        }
    }

    private static void realizarPedido(Scanner scanner) {
        System.out.print("Ingrese el número de mesa: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        Mesa mesa = null;
        for (Mesa m : mesasOcupadas) {
            if (m.getNumeroMesa() == numeroMesa) {
                mesa = m;
                break;
            }
        }

        if (mesa == null) {
            System.out.println("La mesa no está ocupada.");
            return;
        }

        System.out.println("\n--- Carta del Restaurante ---");
        CARTA.forEach((producto, precio) -> System.out.println(producto + " - " + precio + "€"));
        System.out.print("Ingrese el nombre del producto: ");
        String producto = scanner.nextLine();

        if (!CARTA.containsKey(producto)) {
            System.out.println("Producto no disponible en la carta.");
            return;
        }

        mesa.agregarConsumicion(producto, CARTA.get(producto));
        System.out.println("Pedido agregado con éxito.");
    }

    private static void mostrarAsignaciones() {
        if (mesasOcupadas.isEmpty()) {
            System.out.println("No hay mesas ocupadas actualmente.");
        } else {
            System.out.println("\n--- Mesas Ocupadas ---");
            for (Mesa m : mesasOcupadas) {
                System.out.println(m);
            }
        }
    }

    private static void mostrarMesasOrdenadasPorPrecio() {
        if (mesasOcupadas.isEmpty()) {
            System.out.println("No hay mesas ocupadas actualmente.");
        } else {
            List<Mesa> mesasOrdenadas = new ArrayList<>(mesasOcupadas);
            mesasOrdenadas.sort((m1, m2) -> Double.compare(m2.getPrecioTotal(), m1.getPrecioTotal()));

            System.out.println("\n--- Mesas Ordenadas por Precio (Mayor a Menor) ---");
            for (Mesa m : mesasOrdenadas) {
                System.out.println(m);
            }
        }
    }
}
