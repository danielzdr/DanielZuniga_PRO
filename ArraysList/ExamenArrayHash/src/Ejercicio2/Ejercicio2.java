package Ejercicio2;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el numero de alumnos que quieres agregar");
        int numeroAlumnos = scanner.nextInt();
        for (int i = 0; i < numeroAlumnos; i++) {
            System.out.println("Introduce el alumno");
            String nombreAlumno = scanner.next();
            Controladora2.agregarAlumno(nombreAlumno);

        }

        System.out.println("Introduce una respuesta");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("s")){
            Controladora2.calificar(respuesta);
        }

        Controladora2.verSuspensos();
        Controladora2.listarOrdenado();
    }
}