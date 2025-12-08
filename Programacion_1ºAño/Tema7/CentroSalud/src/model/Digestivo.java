package model;

public class Digestivo extends Doctor{

        private int anosExperiencia;




    public Digestivo(String nombre , String apellido , String numeroColegiado , int anosExperiencia) {
        super(apellido , nombre , Integer.parseInt(numeroColegiado));
        this.anosExperiencia = anosExperiencia;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Años de experiencia " +anosExperiencia);
    }

    @Override
    public void realizarAccion() {
        System.out.println("El paciente tiene citas en el especialista digestivo");
    }
}
