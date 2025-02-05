package Ejercicio3Asignaturas.model;

import java.util.ArrayList;

public class Alumno {

    private String nombre;
    private ArrayList<Asignatura> asignaturas;

    public Alumno(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }



    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }


    public String getNombre() {
        return nombre;

    }
}
