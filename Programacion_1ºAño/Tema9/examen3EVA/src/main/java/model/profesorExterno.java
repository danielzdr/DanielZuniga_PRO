package model;


public class profesorExterno extends Profesor{
    private int horas, precioHora;

    public profesorExterno(String nombre, String DNI, int salarioBaseAnual, int horas, int precioHora){
        super(nombre,DNI,salarioBaseAnual);
        this.horas= horas;
        this.precioHora= precioHora;
    }
}
