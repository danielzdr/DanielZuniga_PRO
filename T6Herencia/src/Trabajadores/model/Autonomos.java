package Trabajadores.model;

public class Autonomos extends Trabajadores{
    private String nombre, apellido, dni;
    private int sueldo;
    private boolean contratado;

    public Autonomos() {
    }

    public Autonomos(String nombre , String apellido , String dni , int sueldo , boolean contratado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sueldo = sueldo;
        this.contratado = contratado;
    }

    public Autonomos(String nombre , String apellido , String dni , int sueldo , String nombre1 , String apellido1 , String dni1 , int sueldo1 , boolean contratado) {
        super(nombre , apellido , dni , sueldo);
        this.nombre = nombre1;
        this.apellido = apellido1;
        this.dni = dni1;
        this.sueldo = sueldo1;
        this.contratado = contratado;
    }

    public Autonomos(String nombre , String apellido , String dni , int sueldo) {
        super(nombre , apellido , dni , sueldo);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int getSueldo() {
        return sueldo;
    }

    @Override
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }

    public void mostrarDatos() {
        double salarioAnual = sueldo * 12;
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " DNI: " + dni +
                " Salario Anual: " + salarioAnual);
    }
}
