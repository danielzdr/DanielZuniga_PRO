package Ejercicio8Consumicion;

import Ejercicio8Consumicion.model.Articulo;
import Ejercicio8Consumicion.model.Cuenta;
import Ejercicio8Consumicion.model.Entrada;

import java.util.Scanner;

public class Consumo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
       Entrada entrada= new Entrada();


        int opcion;
        do {
            System.out.println("\n*** Menú de opciones ***");
            System.out.println("1. Registrar cuenta");
            System.out.println("2. Agregar artículo a cuenta");
            System.out.println("3. Obtener total de cuenta");
            System.out.println("4. Cambiar estado de cuenta");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();


            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el identificador de la cuenta: ");
                    int idCuenta = scanner.nextInt();
                    Cuenta cuenta= new Cuenta(idCuenta);
                    entrada.registrarCuenta(cuenta);
                    System.out.println("Cuenta registrada con el identificador " + idCuenta);
                    break;
                case 2:
                    System.out.print("Introduce el identificador de la cuenta: ");
                    int idCuentaArticulo = scanner.nextInt();
                    Cuenta cuentaExistente = entrada.obtenerCuenta(idCuentaArticulo);
                    if (cuentaExistente != null) {
                        System.out.print("Introduce el nombre del artículo: ");
                        scanner.nextLine();  // Consumir el salto de línea
                        String nombreArticulo = scanner.nextLine();
                        System.out.print("Introduce el coste del artículo: ");
                        double costeArticulo = scanner.nextDouble();
                        Articulo articulo = new Articulo(nombreArticulo, costeArticulo);
                        cuentaExistente.agregarArticulo(articulo);
                        System.out.println("Artículo agregado a la cuenta " + idCuentaArticulo);
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Introduce el identificador de la cuenta: ");
                    int idCuentaNumeroArticulos = scanner.nextInt();
                    Cuenta cuentaNumeroArticulos = entrada.obtenerCuenta(idCuentaNumeroArticulos);
                    if (cuentaNumeroArticulos != null) {
                        System.out.println("La cuenta " + idCuentaNumeroArticulos + " tiene " + cuentaNumeroArticulos.getArticulos() + " artículos.");
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Introduce el identificador de la cuenta: ");
                    int idCuentaCoste = scanner.nextInt();
                    entrada.mostrarValorCuenta(idCuentaCoste);
                    break;
                case 5:
                    System.out.println("Introduce el identificador de la cuenta");
                    int identificador= scanner.nextInt();
                    System.out.println("Introduce el nuevo estado de la cuenta: true-> para pagarla, false-> para no pagarla");
                    boolean estado= scanner.hasNextBoolean();
                    entrada.cambiarEstadoCuenta(identificador,estado);
                case 6:
                    System.out.println("Saliendo del programa");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }while (opcion!=7);
    }
}
