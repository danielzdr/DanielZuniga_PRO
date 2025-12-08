package Ejercicio9Casa;

import Ejercicio9Casa.model.CasaException;
import Ejercicio9Casa.model.Terreno;
import Ejercicio9Casa.model.TerrenoException;
import Ejercicio9Casa.util.Orientacion;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;
        Terreno terreno = new Terreno();
        Terreno.Casa casa = terreno.new Casa();
        terreno.setCasa(casa);
        do {
            System.out.println("1. Crear terreno ");
            System.out.println("2. Crear Casa ");
            System.out.println("3. Mostrar datos de la casa ");
            System.out.println("4. Crear la habitacion de la casa ");
            System.out.println("5. Crear piscina de la casa ");
            System.out.println("6. Crear anexo en la vivienda ");
            System.out.println("7. Crear habitacion con m2 superior a los de la casa ");
            System.out.println("8. Mostrar datos de la casa ");
            System.out.println("9. Salir del programa ");
            System.out.println("Introduce una opcion ");
            opcion = scanner.nextInt();
            try {
                switch (opcion) {
                    case 1:

                        System.out.print("Ingrese el tamaño del terreno en m2: ");
                        int m2 = scanner.nextInt();
                        System.out.print("Ingrese la orientación (NORTE, SUR, ESTE, OESTE): ");
                        String orientacionStr = scanner.nextLine();
                        Orientacion orientacion = Orientacion.valueOf(orientacionStr.toUpperCase().toLowerCase());
                        terreno = new Terreno(m2 , orientacion);
                        break;

                    case 2:

                        if (terreno == null) {
                            throw new TerrenoException("Primero debe crear un terreno.");
                        }
                        System.out.print("Ingrese los m2 de la casa: ");
                        m2 = scanner.nextInt();
                        if (!terreno.contruirCasa(m2)) {
                            throw new TerrenoException("No hay suficiente espacio en el terreno para construir la casa.");
                        }
                        System.out.println("Casa construida con éxito.");
                        break;

                    case 3:

                        if (terreno == null || terreno.getCasa() == null) {
                            throw new CasaException("No hay casa construida.");
                        }
                        casa = terreno.getCasa();
                        System.out.println("Datos de la casa:");
                        System.out.println("M2 restantes en la casa: " + casa.getM2());
                        System.out.println("Habitaciones: " + casa.getHabitacion());
                        System.out.println("Piscina: " + (casa.isPiscina() ? "Sí" : "No"));
                        terreno.mostrarDatos();
                        break;

                    case 4:

                        if (terreno == null || terreno.getCasa() == null) {
                            throw new CasaException("No hay casa construida.");
                        }
                        Terreno.Casa casaParaHabitacion = terreno.getCasa();
                        System.out.print("Ingrese los m2 de la habitación: ");
                        int metrosHabitacion = scanner.nextInt();

                        if (!casaParaHabitacion.construirHabitacion(metrosHabitacion)) {
                            throw new CasaException("No hay suficiente espacio para construir la habitación.");
                        }
                        System.out.println("Habitación construida.");
                        break;

                    case 5:

                        if (terreno == null || terreno.getCasa() == null) {
                            throw new CasaException("No hay casa para construir piscina.");
                        }
                        terreno.getCasa().construirPiscina();
                        System.out.println("Piscina construida.");
                        break;

                    case 6:

                        if (terreno == null || terreno.getCasa() == null) {
                            throw new CasaException("No hay casa para construir anexo.");
                        }
                        System.out.print("Ingrese los m2 del anexo: ");
                        int metrosAnexo = scanner.nextInt();
                        terreno.getCasa().contruirAnexo(metrosAnexo);
                        System.out.println("Anexo construido.");
                        break;

                    case 7:

                        if (terreno == null) {
                            throw new TerrenoException("Primero debe crear un terreno.");
                        }
                        terreno.revalorizarCasa();
                        System.out.println("Valoración actual: " + terreno.getValoracion());
                        break;
                    case 8:
                        casa.mostrarDatos();
                        break;
                    case 9:
                        System.out.println("Saliendo del programa.....");
                        break;

                    default:
                        System.out.println("Opción no válida. Intentalo de nuevo");
                }
            } catch (TerrenoException | CasaException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 9);

    }
}