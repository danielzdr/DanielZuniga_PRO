package EjerciciosDel1al3.model;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre, apellidos,telefono;
    private int edad;
    private final static long serialVERSIONUID= 789L;

    public Usuario() {
    }

    public Usuario(String nombre , String apellidos , String telefono , int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono= telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad=" + edad +
                '}';
    }

    public void mostrarDatos(){
        System.out.println("Nombre= " +nombre);
        System.out.println("Apellidos= " +apellidos);
        System.out.println("Correo= " +telefono);
        System.out.println("Edad= " +edad);
    }


}
