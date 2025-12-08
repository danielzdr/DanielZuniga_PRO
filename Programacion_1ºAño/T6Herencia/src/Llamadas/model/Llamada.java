package Llamadas.model;



public abstract class Llamada  {
    protected String nOrigen;
    protected String nDestino;
    protected int duracion;
    protected double coste;

    public Llamada() {
    }

    public Llamada(String nOrigen, String nDestino, int duracion) {
        this.nOrigen = nOrigen;
        this.nDestino = nDestino;
        this.duracion = duracion;
        this.coste = calcularCoste();
    }


    public abstract double calcularCoste();


    public String getnOrigen() {
        return nOrigen;
    }

    public String getnDestino() {
        return nDestino;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getCoste() {
        return coste;
    }


    public void mostrarLlamada() {
        System.out.println("Origen: " + nOrigen + " | Destino: " + nDestino + " | Duración: " + duracion + " segundos | Coste: " + coste + "€");
    }
}