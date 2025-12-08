package Ejercicio3Asignaturas;

import Ejercicio3Asignaturas.model.Alumno;
import Ejercicio3Asignaturas.model.Asignatura;
import Ejercicio3Asignaturas.model.Profesor;


public class Entrada {
    public static void main(String[] args) {


        Asignatura asignatura1 = new Asignatura("Matematicas");
        Asignatura asignatura2 = new Asignatura("Programacion");
        Asignatura asignatura3= new Asignatura("Base de datos");
        Alumno alumno = new Alumno("Daniel Zuñiga");
        alumno.agregarAsignatura(asignatura1);
        alumno.agregarAsignatura(asignatura2);
        alumno.agregarAsignatura(asignatura3);

        Profesor profesor = new Profesor();

        profesor.ponerNotas(alumno,"Matematicas",9);
        profesor.ponerNotas(alumno,"Programacion",6);
        profesor.ponerNotas(alumno,"Base de datos",8.5);

        System.out.println("Calificaciones: " +alumno.getNombre()+ ":"+ asignatura1.getIdentificador() + ":" +asignatura1.getCalificacion());


    }
}
