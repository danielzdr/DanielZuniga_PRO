import Controller.Concesionario;
import DTO.CocheDTO;
import DTO.PasajerosDTO;
import model.Coche;
import model.Pasajeros;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Concesionario concesionario = new Concesionario();
        CocheDTO cocheDTO= new CocheDTO();
        PasajerosDTO pasajerosDTO= new PasajerosDTO();

        int opcion;
        System.out.println("Bienvenido a la App de gestion de un concesionario");
        do {
            System.out.println("1. Añadir coche");
            System.out.println("2. Borrar coche");
            System.out.println("3. Consulta coche");
            System.out.println("4. Listado coches");
            System.out.println("5. Guardar coche");
            System.out.println("6. Exportar CSV");
            System.out.println("7. Gestion de pasajeros");
            System.out.println("8. Terminar el programa");
            System.out.println("Introduce una opcion");
            opcion = scanner.nextInt();
            switch (opcion) {

                case 1:
                    System.out.print("id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Matrícula: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    concesionario.anadirCoche(new Coche(id, matricula, marca,modelo,color));
                    System.out.println("Coche añadido.");
                    break;
                case 2:
                    System.out.println("Matricula del coche a borrar: ");
                       matricula= scanner.nextLine();
                     scanner.nextLine();
                     boolean borrado= concesionario.borrarCoche(matricula);
                     if (borrado){
                         System.out.println("Coche borrado");
                     }else {
                         System.out.println("Coche no encontrado");
                     }

                    break;
                case 3:
                    System.out.println("ID del coche que quieres consultar: ");
                     int idConsulta= scanner.nextInt();
                    scanner.nextLine();
                    Coche cocheEncontrado= concesionario.consultarCoche(idConsulta);
                    if (cocheEncontrado != null) {
                        System.out.println("Coche encontrado");
                        System.out.println(cocheEncontrado);
                    }else {
                        System.out.println("Coche no encontrado");
                    }
                    break;
                case 4:
                    concesionario.listarCoches();
                    break;
                case 5:
                    concesionario.guardarDatos();
                    break;
                case 6:
                    concesionario.exportarCSV();
                    break;
                case 7:
                    boolean salirSubMenu=false;
                    System.out.println("Bienvenido a la gestion de pasajeros");
                    do {
                        System.out.println("1. Añadir pasajero");
                        System.out.println("2. Borrar pasajero");
                        System.out.println("3. Consulta de pasajero");
                        System.out.println("4. Listado de pasajeros");
                        System.out.println("5. Añadir pasajero a coche");
                        System.out.println("6. Borrar pasajero de coche");
                        System.out.println("7. Listar todos los pasajeros de un coche");
                        System.out.println("8. Salir de gestion de pasajeros");
                        System.out.println("Introduce la opcion");
                        int opcionSubMenu= scanner.nextInt();
                        scanner.nextLine();
                        switch (opcionSubMenu){
                            case 1:
                                System.out.print("id: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Nombre: ");
                                String nombre = scanner.nextLine();
                                System.out.print("Edad: ");
                                int edad = scanner.nextInt();
                                System.out.print("Modelo: ");
                                int peso = scanner.nextInt();
                                concesionario.anadirPasajero(new Pasajeros(id,edad,peso,nombre));
                                break;
                            case 2:
                                System.out.println("ID del pasajero que quieres borrar: ");
                                id= scanner.nextInt();
                                scanner.nextLine();
                                borrado= concesionario.borraPasajeros(id);
                                if (borrado){
                                    System.out.println("Pasajero borrado correctamente");
                                }else {
                                    System.out.println("Pasajero no encontrado");
                                }
                                break;
                            case 3:
                                System.out.println("ID del pasajero que quieres consultar: ");
                                idConsulta= scanner.nextInt();
                                scanner.nextLine();
                                Pasajeros pasajeroEncontrado= concesionario.consultarPasajero(idConsulta);
                                if (pasajeroEncontrado != null) {
                                    System.out.println("Pasajero encontrado");
                                    System.out.println(pasajeroEncontrado);
                                }else {
                                    System.out.println("Pasajero no encontrado");
                                }
                                break;
                            case 4:
                                concesionario.listarTodosLosPasjeros();
                                break;
                            case 5:
                                System.out.println("Introduce un id");
                                int idInsert= scanner.nextInt();
                                System.out.println("Introduce un nombre");
                                String nombreInsert= scanner.next();
                                System.out.println("Introduce una edad");
                                int edadInsert= scanner.nextInt();
                                System.out.println("Introduce un peso");
                                int pesoInsert= scanner.nextInt();
                                try {
                                    pasajerosDTO.insertarPasajeros(new Pasajeros(idInsert,edadInsert,pesoInsert,nombreInsert));
                                } catch (SQLException e) {
                                    System.out.println("Error en la base de datos");
                                }
                                break;
                            case 6:
                                System.out.println("Introduce un nombre de un pasajero que quieras borrar");
                                String nombreDelete= scanner.next();
                                pasajerosDTO.borrarPasajeros(nombreDelete);
                                break;
                            case 7:
                                pasajerosDTO.listarPasajeros();
                                break;
                            case 8:
                                salirSubMenu=true;
                                System.out.println("Saliendo de la gestion de pasajeros...");
                                break;
                            default:
                                System.out.println("Opcion no valida, introducela de nuevo");
                                break;
                        }
                    }while (!salirSubMenu);

                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Accion invalida. Intetentalo de nuevo.");
                    break;
            }


        } while (opcion != 8);

    }
}