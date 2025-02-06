package Trabajadores.model;

public class Jefe extends Trabajadores{

    private String nombre, apellido, dni;
    private int acciones, beneficio;


    public Jefe() {
    }

    public Jefe(String nombre , String apellido , String dni , int acciones , int beneficio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.acciones = acciones;
        this.beneficio = beneficio;
    }

    public Jefe(String nombre , String apellido , String dni , int sueldo) {
        super(nombre , apellido , dni , sueldo);
    }

    public Jefe(String nombre , String apellido , String dni , int sueldo , String nombre1 , String apellido1 , String dni1 , int acciones , int beneficio) {
        super(nombre , apellido , dni , sueldo);
        this.nombre = nombre1;
        this.apellido = apellido1;
        this.dni = dni1;
        this.acciones = acciones;
        this.beneficio = beneficio;
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

    public int getAcciones() {
        return acciones;
    }

    public void setAcciones(int acciones) {
        this.acciones = acciones;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " DNI: " + dni +
                " Acciones: " + acciones + " Beneficio: " + beneficio);
    }

    public void despedir(Trabajadores trabajador) {
        System.out.println("El jefe " + nombre + " " + apellido + " ha despedido a " + trabajador.getNombre() + " " + trabajador.getApellido());
    }
}
