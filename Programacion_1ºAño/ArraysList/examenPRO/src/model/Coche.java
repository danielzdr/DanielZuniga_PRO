package model;

public final class Coche extends  Vehiculo {
    private int cv, cc;


    public Coche() {
    }

    public Coche(String estado , String marca , String modelo , String numeroSerie , double precioVenta , int cv , int cc) {
        super(estado , marca , modelo , String.valueOf(numeroSerie) , precioVenta);
        this.cv = cv;
        this.cc = cc;
    }

    public void mostrarDatos(){
        System.out.println("Potencia " +cv+
                            "Centimetros Cubicos " +cc);
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }
}
