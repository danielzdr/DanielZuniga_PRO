package Ejercicio8Consumicion.model;

import java.util.ArrayList;

public class Cuenta {
    private int identificador;
    private boolean estado;
    private ArrayList<Articulo> articulos;


    public Cuenta(int identificdor) {
        this.identificador = identificador;
        this.articulos = new ArrayList<>();
        this.estado = false;
    }

    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);

    }

    public double obtenerTotal() {
        double costeTotal = 0;
        for (Articulo articulo : articulos) {
            costeTotal+= articulo.getCoste();
        }
        return costeTotal;
    }

    public void mostrarDatos(){
       for (Articulo articulo: articulos){
           System.out.println("Articulos = " +articulo.getNombre()+
           ", Coste = " +articulo.getCoste());
       }
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }
}
