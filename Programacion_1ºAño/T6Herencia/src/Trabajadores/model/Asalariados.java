package Trabajadores.model;

public class Asalariados extends Trabajador implements Empleador, Sindicador{

    private double retencion;
    private  int nPagas;


    public Asalariados() {
    }

    public Asalariados(String nombre , String apellido , int nSsocial , double salario , double retencion , int nPagas) {
        super(nombre , apellido , nSsocial , salario);
        this.retencion = retencion;
        this.nPagas = nPagas;
    }

    public Asalariados(String nombre , String apellido , int nSsocial , double salario) {
        super(nombre , apellido , nSsocial , salario);
        this.nPagas=12;
        this.retencion=0.02;
    }



    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Retencion = " +retencion);
        System.out.println("Numero de Pagas = " +nPagas);
    }

    @Override
    public void calcularSalarioMes() {
        double salarioMes= getSalario()-(getSalario()*retencion)/nPagas;
        System.out.println("El salario mensual al mes es de " +salarioMes);
    }

    public boolean realizarTrabajo(int nHoras){
        System.out.println("El asalariado se dispone a realizar el trabajo");
        return true;
    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }

    public int getnPagas() {
        return nPagas;
    }

    public void setnPagas(int nPagas) {
        this.nPagas = nPagas;
    }
}
