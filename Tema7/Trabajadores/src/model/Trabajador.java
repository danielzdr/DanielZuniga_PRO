package model;

abstract public class Trabajador {
    private String nombre, apellido;
    private int nSsocial;
    double salario;

    public Trabajador() {
    }

    public Trabajador(String nombre , String apellido , int nSsocial , double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nSsocial = nSsocial;
        this.salario = salario;
    }

    public void mostrarDatos(){
        System.out.println("Nombre = " +nombre);
        System.out.println("Apellido = " +apellido);
        System.out.println("Numero de la seguridad Social = " +nSsocial);
        System.out.println("Salario = " +salario);
    };


    public abstract void calcularSalarioMes();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getnSsocial() {
        return nSsocial;
    }

    public void setnSsocial(int nSsocial) {
        this.nSsocial = nSsocial;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
