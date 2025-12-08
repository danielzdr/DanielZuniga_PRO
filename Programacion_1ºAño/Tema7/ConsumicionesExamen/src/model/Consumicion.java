package model;

 abstract public class Consumicion {
    private String nombre;
    private double precio;

    public Consumicion() {
    }

    public Consumicion(String nombre , double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void mostrarDatos(){
        System.out.println("Nombre"+this.nombre);
        System.out.println("Precio"+this.precio);
    }


 }
