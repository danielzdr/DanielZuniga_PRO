package Llamadas.model;

public class LlamadasNacionales extends Llamada {


    private int franja;

    public LlamadasNacionales(String nOrigen , String nDestino , int duracion , int franja) {
        super(nOrigen , nDestino , duracion);
        this.franja = franja;
    }

    @Override
    public double calcularCoste() {
        double costeSegundo = 0;

        switch (franja) {
            case 1:
                double costePorSegundo = 0.20;
                break;
            case 2:
               costePorSegundo = 0.25;
                break;
            case 3:
                costePorSegundo = 0.30;
                break;
            default:
                throw new IllegalArgumentException("Franja horaria no válida");
        }
        return duracion * costeSegundo;
    }
}
