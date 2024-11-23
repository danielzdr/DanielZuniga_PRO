package ExamenArraysDanielZuñiga;

import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        String[][] usuarios = new String[10][4];
        int totalUsu = 0;
        int opcion;

        do {
            System.out.println("Gestión de Usuarios");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Mostrar Usuarios");
            System.out.println("3. Salir del programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    if (totalUsu < 10) {
                        System.out.println("Ingrese los datos del usuario:");
                        System.out.print("Nombre: ");
                        usuarios[totalUsu][0] = scanner.nextLine();
                        System.out.print("Apellido: ");
                        usuarios[totalUsu][1] = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        usuarios[totalUsu][2] = scanner.nextLine();
                        System.out.print("DNI: ");
                        usuarios[totalUsu][3] = scanner.nextLine();

                        totalUsu++;
                        System.out.println("Usuario agregado con éxito.");
                    } else {
                        System.out.println("No se pueden agregar más usuarios. La lista está llena.");
                    }
                    break;

                case 2:
                    System.out.println("Listado de Usuarios:");
                    if (totalUsu == 0) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        for (int i = 0; i < totalUsu; i++) {
                            System.out.println((i + 1) + "Nombre: " + usuarios[i][0]
                                    + ", Apellido: " + usuarios[i][1]
                                    + ", Teléfono: " + usuarios[i][2]
                                    + ", DNI: " + usuarios[i][3]);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intentalo de nuevo.");
                    break;
            }

            System.out.println();
        } while (opcion != 3);
    }
}
