package ListarMultimedia;

import ListarMultimedia.Controller.Coleccion;
import ListarMultimedia.model.*;


import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        ArrayList<Persona> actores= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Persona persona= new Persona();
        System.out.println(persona.getNombre());
        Audio audio=new Audio();
        new Persona();
        Coleccion coleccion= new Coleccion();
        actores.add(new Persona("Actor1","1234"));
        actores.add(new Persona("Actor2","123"));
        Video video = new Video("123","Titulo",100,"mp3",
                new Persona("Autor","123"),new Persona("Director","1234A"),actores);
        video.mostrarDatos();
        int opcion;
       do {
           System.out.println("1. Añadir a la colección");
           System.out.println("2. Eliminar de la colección");
           System.out.println("3. Listar elementos");
           System.out.println("4. Buscar elementos");
           System.out.println("5. Salir");
           System.out.print("Elige una opción: ");
           opcion = scanner.nextInt();
           scanner.nextLine();

           switch (opcion) {
               case 1:
                   coleccion.añadirElemento(String.valueOf(scanner));
                   break;
               case 2:
                   coleccion.eliminarElemento(String.valueOf(scanner));
                   break;
               case 3:
                   coleccion.listarElementos();
                   break;
               case 4:
                   coleccion.buscarElemento(scanner);
                   break;
               case 5:
                   System.out.println("Salir del programa....");;
                   break;

               default:
                   System.out.println("Opción no válida.");
           }
       }while(opcion!=5);


    }
}
