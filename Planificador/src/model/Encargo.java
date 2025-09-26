package model;

public class Encargo implements ElementoPlanificable{
    private String descripcion;
    private boolean completa;
    private int id;

    public Encargo() {
    }

    public Encargo(String descripcion , boolean completa , int id) {
        this.descripcion = descripcion;
        this.completa = false;
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void completar(){
        this.completa=true;
    }

    public void mostrarDatos(){
        System.out.println("Id"+id);
        System.out.println("Descripcion"+descripcion);
        System.out.println("Completada"+completa);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " Descripción: " + descripcion +
                " Completado: " + (completa ? "Sí" : "No");
    }
}
