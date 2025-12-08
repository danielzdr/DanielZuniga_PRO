package model;

abstract public class Doctor extends Persona implements AccionesDoctor{
    public int numeroColegiado;
    public Especialidad especialidad;



    public Doctor(String apellidos , String nombre , int numeroColegiado) {
        super(apellidos , nombre);
        this.numeroColegiado = numeroColegiado;
    }

    public int getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(int numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Numero de colegiado " +numeroColegiado);
    }

    public abstract void realizarAccion();
}
