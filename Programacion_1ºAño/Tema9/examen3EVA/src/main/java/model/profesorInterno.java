package model;


public class profesorInterno extends Profesor{
    private int complementoFijoMensual;

    public profesorInterno(String nombre, String DNI, int salarioBaseAnual, int complementoFijoMensual){
        super(nombre,DNI,salarioBaseAnual);
        this.complementoFijoMensual= complementoFijoMensual;
    }

    public void calcularSalarioProfesorInterno(){
        int complementoFijo= 500;

    }


}
