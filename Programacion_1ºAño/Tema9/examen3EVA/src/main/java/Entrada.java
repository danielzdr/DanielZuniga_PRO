import controller.Gestion;
import dto.ProfesorDTO;
import model.Profesor;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gestion gestion= new Gestion();
        int opcion;
        do {
            System.out.println("1. Mostrar Profesores base de datos");
            System.out.println("2. Agregar profesor temporal");
            System.out.println("3. Dar de baja profesor temporal");
            System.out.println("4. Exportar datos a basse de datos");
            System.out.println("5. Exportar datos a csv");
            System.out.println("6. Exportar datos a fichero obj");
            System.out.println("7. Salir del programa");
            System.out.println("Introduce un opcion");
            opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    gestion.mostrarProfesores();
                    break;
                case 2:
                    System.out.println("Introduce el nombre de un profesor");
                    String nombre= scanner.next();
                    System.out.println("Introduce el DNI del profesor");
                    String DNI= scanner.next();
                    System.out.println("Introduce el salario del profesor");
                    int salarioBase= scanner.nextInt();
                    gestion.agregarListaLocal( new Profesor(nombre,DNI,salarioBase));

                    break;
                case 3:
                    System.out.println("Introduce el DNI de un profesor que quieras borrar: ");
                    String dniBorrar= scanner.next();
                    gestion.borrarListaLocal(dniBorrar);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    gestion.exportarFicheroOBJ();
                    break;
                case 7:
                    System.out.println("Saliendo de la aplicacion");
                    break;
                default:
                    System.out.println("Opcion no valida, intentalo de nuevo");
                    break;

            }
        }while(opcion!=7);
    }
}
