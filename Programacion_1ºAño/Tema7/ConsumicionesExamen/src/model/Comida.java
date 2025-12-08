package model;

public class Comida extends Consumicion implements  Inventariable{
    private int calorias;

    public Comida() {
    }

    public Comida(String nombre , double precio , int calorias) {
        super(nombre , precio);
        this.calorias = calorias;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Calorias"+this.calorias);
    }

    @Override
    public void almacenar() {
        System.out.println("Esta consumicion se almacena de forma apilable");
    }
}
