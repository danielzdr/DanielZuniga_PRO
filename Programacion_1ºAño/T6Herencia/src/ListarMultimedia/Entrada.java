package ListarMultimedia;

import ListarMultimedia.Controller.Coleccion;
import ListarMultimedia.model.*;


import java.util.*;

public class Entrada {
    public static void main(String[] args) {
        ArrayList<Persona> actores= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Persona persona= new Persona();
        System.out.println(persona.getNombre());
        new Persona();

        int opcion;
       do {
           System.out.println("1. Añadir a la colección");
           System.out.println("2. Eliminar de la colección");
           System.out.println("3. Listar elementos");
           System.out.println("4. Buscar elementos");
           System.out.println("5. Buscar por autor");
           System.out.println("6. Buscar por actor");
           System.out.println("7. Buscar por director");
           System.out.println("8. Salir");
           System.out.print("Elige una opción: ");
           opcion = scanner.nextInt();
           scanner.nextLine();

           switch (opcion) {
               case 1:
                   añadirElemento(scanner);
                   break;
               case 2:
                   eliminarElemento(scanner);
                   break;
               case 3:
                   coleccion.listarElementos(scanner);
                   break;
               case 4:
                   buscarPorAutor(scanner);
                   break;
               case 5:
                   buscarPorActor(scanner);
                   break;
               case 6:
                   buscarPorDirector(scanner);
                   break;
               case 7:
                   System.out.println("Salir del programa....");;
                   break;

               default:
                   System.out.println("Opción no válida.");
           }
       }while(opcion!=9);




        }

        static Coleccion coleccion= new Coleccion();
    private static void añadirElemento(Scanner scanner) {
        System.out.print("Introduce el tipo de objeto (libro/video/audio): ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("Introduce el ID: ");
        String id = scanner.nextLine();
        for (Elemento elemento : coleccion.getElementos()) {
            if (elemento.getId().equals(id)) {
                System.out.println("¡Error! Ya existe un elemento con ese ID.");
                return;
            }
        }

        System.out.print("Introduce el título: ");
        String titulo = scanner.nextLine();
        System.out.print("Introduce el autor: ");
        String autor = scanner.nextLine();
        System.out.print("Introduce el tamaño (MB): ");
        double tamaño = scanner.nextDouble();
        scanner.nextLine();  // Limpiar buffer
        System.out.print("Introduce el formato: ");
        String formato = scanner.nextLine();

        if (tipo.equals("libro")) {
            System.out.print("Introduce el ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Introduce el número de páginas: ");
            int numPaginas = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            coleccion.añadirElemento(new Libro());
        } else if (tipo.equals("video")) {
            System.out.print("Introduce el nombre del director: ");
            String nombreDirector = scanner.nextLine();
            System.out.print("Introduce el DNI del director: ");
            String dniDirector = scanner.nextLine();
            Persona director = new Persona(nombreDirector, dniDirector);

            Set<Persona> actor= new HashSet<>();
            System.out.print("Introduce el número de actores: ");
            int numActores = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            for (int i = 0; i < numActores; i++) {
                System.out.print("Introduce el nombre del actor: ");
                String nombreActor = scanner.nextLine();
                System.out.print("Introduce el DNI del actor: ");
                String dniActor = scanner.nextLine();
                Persona persona=(new Persona(nombreActor, dniActor));
            }

        } else if (tipo.equals("audio")) {
            System.out.print("Introduce la duración (minutos): ");
            double duracion = scanner.nextDouble();
            scanner.nextLine();  // Limpiar buffer
            System.out.print("Introduce el soporte: ");
            String soporte = scanner.nextLine();

        }

        System.out.println("Elemento añadido correctamente.");
    }

    private static void eliminarElemento(Scanner scanner) {
        System.out.print("Introduce el ID del elemento a eliminar: ");
        String id = scanner.nextLine();
        boolean eliminado = false;
        ArrayList<Elemento> iterator = coleccion.getElementos();

        if (eliminado) {
            System.out.println("Elemento eliminado correctamente.");
        } else {
            System.out.println("No se encontró un elemento con ese ID.");
        }
    }

    static Scanner scanner = new Scanner(System.in);
    private static void listarElemento() {
        System.out.println("¿Qué tipo de elementos quieres listar?");
        System.out.println("1. Video");
        System.out.println("2. Audio");
        System.out.println("3. Todos");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        for (Elemento elemento : coleccion.getElementos()) {
            if (opcion == 3 || (opcion == 1 && elemento instanceof Video) || (opcion == 2 && elemento instanceof Audio)) {
                elemento.mostrarDatos();
            }
        }
    }





    Scanner scanner1 = scanner;




    private static void buscarPorActor(Scanner scanner) {
        System.out.print("Introduce el nombre del actor: ");
        String actor = scanner.nextLine();
        Iterable<? extends Elemento> coleccion = null;
        for (Elemento elemento : coleccion) {
            if (elemento instanceof Video) {
                Video video = (Video) elemento;
                for (Persona p : video.getActores()) {
                    if (p.getNombre().equalsIgnoreCase(actor)) {
                        System.out.println(video.getTitulo());
                    }
                }
            }
        }
    }


    private static void buscarPorAutor(Scanner scanner) {
        System.out.print("Introduce el nombre del director: ");
        String director = scanner.nextLine();
        Iterable<? extends Elemento> coleccion = null;
        for (Elemento elemento : coleccion) {
            if (elemento instanceof Video && ((Video) elemento).getDirector().getNombre().equalsIgnoreCase(director)) {
                System.out.println(((Video) elemento).getTitulo());
            }
        }
    }

    private static void buscarPorDirector(Scanner scanner) {
        System.out.print("Introduce el nombre del director: ");
        String director = scanner.nextLine();
        for (Elemento elemento : coleccion.getElementos()) {
            if (elemento instanceof Video && ((Video) elemento).getDirector().getNombre().equalsIgnoreCase(director)) {
                System.out.println(((Video) elemento).getTitulo());
            }
        }
    }

    }



