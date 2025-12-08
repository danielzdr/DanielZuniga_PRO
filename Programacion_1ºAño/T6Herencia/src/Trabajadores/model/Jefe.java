package Trabajadores.model;

public abstract class Jefe extends Trabajador implements Empleador,Beneficiario{

    protected String nombre,apellido;


    public Jefe() {
    }

    public Jefe(String nombre , String apellido , int nSsocial , double salario) {
        super(nombre , apellido , nSsocial , salario);
    }


    public boolean realizarTrabajo(int nHoras){
        System.out.println("El jefe se dispone a trabajar un numero determinado de horas");
        if (nHoras>2){
            return false;
        }
        return  true;
    }

    @Override
    public void calcularSalarioMes() {
        System.out.println("El salario mensual es " +getSalario());
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
    }

    @Override
    public void repartirBeneficio(int beneficio) {
        double beneficioTotal= acciones * beneficio;
        setSalario(getSalario()+beneficioTotal);
    }

    public void despedir(Trabajadores trabajador) {
        System.out.println("El jefe " + nombre + " " + apellido + " ha despedido a " + trabajador.getNombre() + " " + trabajador.getApellido());
    }
}
