package model;


public final class Moto extends Vehiculo {
    private String transmision;
    private  double peso;


    public Moto() {
    }

    public Moto(String estado , String marca , String modelo , String numeroSerie , double precioVenta , String transmision , double peso) {
        super(estado , marca , modelo , String.valueOf(numeroSerie) , precioVenta);
        this.transmision = transmision;
        this.peso = peso;
    }

    public void mostrarDatos(){
        System.out.println("Transmision= " +transmision+
                            "Peso= " +peso);
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
