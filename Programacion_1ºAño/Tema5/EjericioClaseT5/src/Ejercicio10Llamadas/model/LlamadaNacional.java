package Ejercicio10Llamadas.model;

public class LlamadaNacional {

    private long numeroOrigen, numeroDestino;
    private double coste, duracion;
    private int destino;

    public LlamadaNacional(long numeroOrigen , long numeroDestino , double coste , double duracion , int destino) {
        this.numeroOrigen = numeroOrigen;
        this.numeroDestino = numeroDestino;
        this.coste = coste;
        this.duracion = duracion;
        this.destino = destino;
        calcularCoste();
    }

    public LlamadaNacional() {
    }

    private void calcularCoste() {
        double costePorSegundo = 0.0;

        switch (destino) {
            case 'A':
                costePorSegundo = 0.40;
                break;
            case 'B':
                costePorSegundo = 0.50;
                break;
            case 'C':
                costePorSegundo = 0.60;
                break;
            default:
                costePorSegundo = 0.70;
                break;
        }


        coste = duracion * costePorSegundo;
    }

    public void mostrarDatos() {
        System.out.println("Datos de la llamada:");
        System.out.println("Número de origen: " + numeroOrigen);
        System.out.println("Número de destino: " + numeroDestino);
        System.out.println("Coste: " + coste + " euros");
        System.out.println("Duración: " + duracion + " segundos");
        System.out.println("Destino (Código): " + destino);
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

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
}
