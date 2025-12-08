package model;

public class Persona {
    private String nombre, apellidos;

    public Persona() {
    }

    public Persona(String apellidos , String nombre) {
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarDatos(){
        System.out.println("Nombre " +nombre);
        System.out.println("Apellidos " +apellidos);
    }
}
