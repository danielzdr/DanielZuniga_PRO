package Ejercicio9Gasolinera.model;

import java.util.ArrayList;

public class Gasolinera {

    private String nombre;
    private  ArrayList<Surtidor> listaSurtidor;
    private int ganancias;

    public Gasolinera(String nombre) {
        this.nombre = nombre;
        listaSurtidor = null;
        this.ganancias = 0;
    }

    public int obtenerGanancias(){

        return ganancias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Surtidor> getListaSurtidor() {
        return listaSurtidor;
    }

    public void setListaSurtidor(ArrayList<Surtidor> listaSurtidor) {
        this.listaSurtidor = listaSurtidor;
    }

    public int getGanancias() {
        return ganancias;
    }

    public void setGanancias(int ganancias) {
        this.ganancias = ganancias;
    }
}
