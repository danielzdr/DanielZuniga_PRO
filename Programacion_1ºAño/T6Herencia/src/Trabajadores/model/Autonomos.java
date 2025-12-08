package Trabajadores.model;

public abstract class Autonomos extends Trabajador implements Sindicador,Beneficio{
    private int cuotaSS;

    public Autonomos() {
    }


    public Autonomos(String nombre , String apellido , int nSsocial , double salario , int cuotaSS) {
        super(nombre , apellido , nSsocial , salario);
        this.cuotaSS = cuotaSS;
    }

    @Override
    public void calcularSalarioMes() {
        double salarioMes= getSalario()-(cuotaSS*12);
        System.out.println("El salario mensual al mes es de " +salarioMes);
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Cuotas de la Seguridad Social = " +cuotaSS);
    }

    public int getCuotaSS() {
        return cuotaSS;
    }

    public void setCuotaSS(int cuotaSS) {
        this.cuotaSS = cuotaSS;
    }
}
