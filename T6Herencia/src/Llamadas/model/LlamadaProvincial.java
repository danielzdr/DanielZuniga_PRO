package Llamadas.model;

public class LlamadaProvincial extends Llamada {

    public LlamadaProvincial(String nOrigen, String nDestino, int duracion) {
        super(nOrigen, nDestino, duracion);
    }

    @Override
    public double calcularCoste() {
        return duracion * 0.15;
    }
}
