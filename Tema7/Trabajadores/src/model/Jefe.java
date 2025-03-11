package model;

public abstract class Jefe extends Trabajador implements  Empleador{


    public Jefe() {
    }

    public Jefe(String nombre , String apellido , int nSsocial , double salario) {
        super(nombre , apellido , nSsocial , salario);
    }


    public boolean realizarTrabajo(int nHoras){
        System.out.println();
        return  false;
    }

    @Override
    public void calcularSalarioMes() {
        System.out.println("El salario mensual es " +getSalario());
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
    }
}

