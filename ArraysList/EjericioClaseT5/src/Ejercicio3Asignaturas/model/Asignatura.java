package Ejercicio3Asignaturas.model;

public class Asignatura {
    private String identificador;
    private double calificacion;

    public Asignatura(String identificador){
        this.identificador=identificador;
    }

    public Asignatura(){}

    public String getIdentificador() {
        return identificador;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}

