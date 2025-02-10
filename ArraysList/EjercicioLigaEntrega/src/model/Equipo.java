package model;

import java.util.ArrayList;


public class Equipo {
    private String nombre;
    private ArrayList<Jugador> jugadores;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesFavor;
    private int golesContra;
    private int posicion;

    public Equipo(String nombre, int posicion) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.puntos = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.posicion=posicion;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void actualizarEstadisticas(int golesFavor, int golesContra, String resultado) {
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;
        this.partidosJugados++;

        if (resultado.equals("victoria")) {
            this.puntos += 3;
            this.partidosGanados++;
        } else if (resultado.equals("empate")) {
            this.puntos += 1;
            this.partidosEmpatados++;
        } else {
            this.partidosPerdidos++;
        }
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    @Override
    public String toString() {
        return nombre + " - Puntos: " + puntos + ", Jugados: " + partidosJugados + ", Ganados: " + partidosGanados +
                ", Empatados: " + partidosEmpatados + ", Perdidos: " + partidosPerdidos + ", Goles a favor: " +
                golesFavor + ", Goles en contra: " + golesContra;
    }
}
