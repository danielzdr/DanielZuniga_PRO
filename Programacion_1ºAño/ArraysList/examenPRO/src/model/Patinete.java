package model;

public final class Patinete extends Vehiculo{

    private int autonomia;

    public Patinete() {
    }

    public Patinete(String estado , String marca , String modelo , String numeroSerie , double precioVenta , int autonomia) {
        super(estado , marca , modelo , numeroSerie , precioVenta);
        this.autonomia = autonomia;
    }

    public void mostrarDatos(){
        System.out.println("Autonomia= " +autonomia);
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
}
