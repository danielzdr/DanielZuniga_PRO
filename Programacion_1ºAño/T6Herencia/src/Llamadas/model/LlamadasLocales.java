package Llamadas.model;



public class LlamadasLocales extends Llamada{
    public LlamadasLocales(String nOrigen, String nDestino, int duracion) {
        super(nOrigen, nDestino, duracion);
    }

    @Override
    public double calcularCoste() {
        return 0;
    }
}
