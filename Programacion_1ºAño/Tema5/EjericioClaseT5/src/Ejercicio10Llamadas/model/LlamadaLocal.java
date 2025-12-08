package Ejercicio10Llamadas.model;

public class LlamadaLocal {

    private long numeroOrigen,numeroDestino;
    private double coste, duracion;

    public LlamadaLocal(long numeroOrigen , long numeroDestino , double duracion) {
        this.numeroOrigen = numeroOrigen;
        this.numeroDestino = numeroDestino;
        this.coste = this.duracion + 0.50;
        this.duracion = 0.15;
    }

    public LlamadaLocal() {
    }

    public void mostrarDatos(){
        System.out.println("Numero Origen = " +this.numeroOrigen +
                            "Numero Destino = " +this.numeroDestino +
                            "Coste = " +this.coste +
                             "Duracion = " +this.duracion);
    }

    public long getNumeroOrigen() {
        return numeroOrigen;
    }

    public void setNumeroOrigen(long numeroOrigen) {
        this.numeroOrigen = numeroOrigen;
    }

    public long getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(long numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
