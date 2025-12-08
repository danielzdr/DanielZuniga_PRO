package model;

public abstract class Autonomo extends  Trabajador implements Sindicador{
    private int cuotaSS;

    public Autonomo() {
    }


    public Autonomo(String nombre , String apellido , int nSsocial , double salario , int cuotaSS) {
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
