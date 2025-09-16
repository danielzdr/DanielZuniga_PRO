package AplicacionCoches.utils;

public enum Incremento {
    DEPORTIVO(0.25),
    TODOTERRENO(0.10),
    ;
    private final double porcentaje;

    Incremento(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double aplicarIncremento(double cv){
        return cv + (cv*porcentaje);
    }
}
