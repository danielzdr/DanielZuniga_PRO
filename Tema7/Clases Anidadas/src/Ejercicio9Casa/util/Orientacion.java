package Ejercicio9Casa.util;

public enum Orientacion {
    NORTE(1.1),
    SUR(1.1),
    ESTE(1.05),
    OESTE(1.05);
    private double revalorizacion;

    Orientacion(double revalorizacion) {
        this.revalorizacion = revalorizacion;
    }

    public double getRevalorizacion() {
        return revalorizacion;
    }


}
