package Ejercicio8Consumicion.model;

public class Articulo {

    private String nombre;
    private double coste;

    public Articulo(String nombre , double coste) {
        this.nombre = nombre;
        this.coste = coste;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}
