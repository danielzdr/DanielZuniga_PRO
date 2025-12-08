package model;

public class Racion extends Consumicion {
    private int cantidad;

    public Racion() {
    }

    public Racion(String nombre , double precio , int cantidad) {
        super(nombre , precio);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Cantidad" +cantidad);
    }
}
