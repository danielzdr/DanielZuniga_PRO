package Ejercicio3Asignaturas.model;

import java.util.ArrayList;

public class Profesor{

    private Alumno alumno;

    public Profesor(){}

    public void ponerNotas(Alumno alumno,String nombreAsignatura, double calificacion){

        for (Asignatura asignatura: alumno.getAsignaturas()) {
            if (asignatura.getIdentificador().equals(nombreAsignatura)){
                asignatura.setCalificacion(calificacion);
                return;
            }

        }
    }

    public double calcularMedia(Alumno alumno) {

        double suma = 0;
        ArrayList<Asignatura> asignaturas = alumno.getAsignaturas();

        for (Asignatura asignatura : asignaturas) {
            suma += asignatura.getCalificacion();
        }

        return suma / asignaturas.size();
    }
}

