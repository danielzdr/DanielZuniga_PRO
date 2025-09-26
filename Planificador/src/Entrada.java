import Controller.Planificador;
import model.Encargo;
import model.Tarea;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Tarea tarea= new Tarea();
        Planificador planificador= new Planificador();
        Scanner scanner= new Scanner(System.in);
        System.out.println("Vamos a realizar un menu de la planificacion de tareas");
        int opcion;
        do {
            System.out.println("Introduce la opcion");
            opcion= scanner.nextInt();
            System.out.println("1. Registrar tarea: ");
            System.out.println("2. Modificar tarea: ");
            System.out.println("3. Listar tareas: ");
            System.out.println("4. Completar tareas: ");
            System.out.println("5. Listar tareas completas: ");
            System.out.println("6. Listar tareas incompletas");
            System.out.println("7. Salir del programa");
            switch (opcion){
                case 1:
                    planificador.registrarTarea(new Encargo("Voy a barrer mi casa", true, 1));
                    planificador.registrarTarea(new Encargo("Vamos a limpiar el coche", true, 2));
                    planificador.registrarTarea(new Encargo("Voy a pasear a los perritos", false, 3));
                    break;
                case 2:
                    System.out.println("Introduce el ID de la tarea a modificar:");
                    int id = scanner.nextInt();
                    System.out.println("Introduce la nueva descripción:");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.println("¿Está completada? (true/false):");
                    boolean completado = scanner.nextBoolean();
                    boolean modificada = planificador.modificarTarea(id, nuevaDescripcion, completado);
                    if (!modificada) {
                        System.out.println("No se encontró ninguna tarea con ese ID");
                    }
                    break;
                case 3:
                    planificador.listarTarea();
                    break;
                case 4:
                    planificador.completarTarea();
                    break;
                case  5:
                    planificador.listarTareaCompleta();
                    break;
                case 6:
                    planificador.listarTareasIncompletas();
                    break;
                case 7:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion invalida, intentalo de nuevo");
                    break;
            }
        }while (opcion!=9);
    }
}
