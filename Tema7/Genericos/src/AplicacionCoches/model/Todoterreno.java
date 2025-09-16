package AplicacionCoches.model;

import AplicacionCoches.utils.Incremento;

public class Todoterreno extends Coche{
    private int traccion;
    private Incremento incremento;

    public Todoterreno() {
    }

    public Todoterreno(String marca , String modelo , String matricula , int cv , int cc , double precio , int traccion , Incremento incremento) {
        super(marca , modelo , matricula , cv , cc , precio);
        this.traccion = traccion;
        this.incremento = incremento;
    }

    public int getTraccion() {
        return traccion;
    }

    public void setTraccion(int traccion) {
        this.traccion = traccion;
    }

    public Incremento getIncremento() {
        return incremento;
    }

    public void setIncremento(Incremento incremento) {
        this.incremento = incremento;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Traccion: " +traccion);
        System.out.println("Incremento: " +incremento);
    }
}
