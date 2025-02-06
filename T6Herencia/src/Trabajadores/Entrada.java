package Trabajadores;

import Trabajadores.Controller.Empresa;
import Trabajadores.model.Asalariados;
import Trabajadores.model.Autonomos;
import Trabajadores.model.Jefe;

import java.util.Scanner;



public class Entrada {

    static Jefe jefe= new Jefe();
     static Autonomos autonomos= new Autonomos();
    static  Asalariados asalariados= new Asalariados();
    static Scanner scanner= new Scanner(System.in);
    static Empresa empresa = new Empresa();
    public static void main(String[] args) {


        Scanner scanner= new Scanner(System.in);
        boolean continuar=true;
        int opcion;

        do {

            System.out.println("Menú:");
            System.out.println("1. Registrar trabajador");
            System.out.println("2. Listar trabajadores");
            System.out.println("3. Mostrar datos de trabajador");
            System.out.println("4. Despedir trabajador");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    registrarTrabajador();
                    break;
                case 2:
                    listarTrabajadores();
                    break;
                case 3:
                    mostrarDatosTrabajador();
                    break;
                case 4:
                    despedirTrabajador();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }while (opcion!=6);
    }

    private static void registrarTrabajador() {
        System.out.print("¿Qué tipo de trabajador desea registrar? (1: Asalariado, 2: Autónomo, 3: Jefe): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Sueldo mensual: ");
            int sueldo = scanner.nextInt();
            System.out.print("Número de pagas: ");
            int numPagas = scanner.nextInt();
            System.out.print("¿Está contratado? (true/false): ");
            boolean contratado = scanner.nextBoolean();
            Asalariados asalariado = new Asalariados(nombre, apellido, dni, sueldo, numPagas, contratado);
            empresa.registrarTrabajador(asalariado);
        } else if (tipo == 2) {
            System.out.print("Sueldo mensual: ");
            int sueldo = scanner.nextInt();
            System.out.print("¿Está contratado? (true/false): ");
            boolean contratado = scanner.nextBoolean();
            Autonomos autonomo = new Autonomos(nombre, apellido, dni, sueldo, contratado);
            empresa.registrarTrabajador(autonomo);
        } else if (tipo == 3) {
            System.out.print("Acciones: ");
            int acciones = scanner.nextInt();
            System.out.print("Beneficio: ");
            int beneficio = scanner.nextInt();
            Jefe jefe = new Jefe(nombre, apellido, dni, acciones, beneficio);
            empresa.registrarTrabajador(jefe);
        } else {
            System.out.println("Tipo de trabajador no válido.");
        }
    }

    private static void listarTrabajadores() {
        System.out.print("¿Desea listar todos los trabajadores, asalariados o autónomos? ");
        String tipo = scanner.nextLine();
        empresa.listarTrabajadores(tipo);
    }

    private static void mostrarDatosTrabajador() {
        System.out.print("Introduce el DNI del trabajador: ");
        String dni = scanner.nextLine();
        empresa.mostrarDatos(dni);
    }

    private static void despedirTrabajador() {
        System.out.print("Introduce el DNI del trabajador a despedir: ");
        String dni = scanner.nextLine();
        empresa.despedirTrabajador(dni);
    }
}

