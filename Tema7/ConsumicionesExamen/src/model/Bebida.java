package model;

public class Bebida extends Consumicion implements Inventariable{
    private int mililitros;

    public Bebida() {
    }

    public Bebida(String nombre , double precio , int mililitros) {
        super(nombre , precio);
        this.mililitros = mililitros;
    }

    public int getMililitros() {
        return mililitros;
    }

    public void setMililitros(int mililitros) {
        this.mililitros = mililitros;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Mililitros "+this.mililitros);
    }

    @Override
    public void almacenar() {
        System.out.println("Esta consumicion se almacena  de forma apilable ");
    }
}
