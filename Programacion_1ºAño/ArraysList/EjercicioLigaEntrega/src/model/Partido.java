package model;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public void jugar() {
        if (golesLocal > golesVisitante) {
            equipoLocal.actualizarEstadisticas(golesLocal, golesVisitante, "victoria");
            equipoVisitante.actualizarEstadisticas(golesVisitante, golesLocal, "derrota");
        } else if (golesLocal < golesVisitante) {
            equipoLocal.actualizarEstadisticas(golesLocal, golesVisitante, "derrota");
            equipoVisitante.actualizarEstadisticas(golesVisitante, golesLocal, "victoria");
        } else {
            equipoLocal.actualizarEstadisticas(golesLocal, golesVisitante, "empate");
            equipoVisitante.actualizarEstadisticas(golesVisitante, golesLocal, "empate");
        }
    }

    @Override
    public String toString() {
        return "Partido: " + equipoLocal.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante.getNombre();
    }
}
