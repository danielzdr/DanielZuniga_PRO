package model;

import java.io.FilePermissionCollection;

public class Paciente extends Persona{
    public FilePermissionCollection citas;
    private int numeroSS;
    private String dolencia;





    public Paciente(String nombre , String apellidos , String nssPaciente , String dolenciaPaciente) {
        super(apellidos , nombre);
        this.dolencia = dolencia;
        this.numeroSS = numeroSS;
    }

    public String getDolencia() {
        return dolencia;
    }

    public void setDolencia(String dolencia) {
        this.dolencia = dolencia;
    }

    public int getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(int numeroSS) {
        this.numeroSS = numeroSS;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Numero de la Seguridad Social " +numeroSS);
        System.out.println("Dolencia " +dolencia);
    }
}
