package model;

public class Jugador {
    private String nombre;
    private int goles;
    private int asistencias;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.goles = 0;
        this.asistencias = 0;
    }

    public void marcarGol() {
        goles++;
    }

    public void darAsistencia() {
        asistencias++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGoles() {
        return goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    @Override
    public String toString() {
        return nombre + " - Goles: " + goles + ", Asistencias: " + asistencias;
    }


}
