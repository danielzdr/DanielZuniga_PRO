package AplicacionCoches.model;

import AplicacionCoches.utils.Incremento;

public class Deportivo extends Coche implements Gestionable{
    private int par;
    private Incremento incremento;

    public Deportivo() {
    }

    public Deportivo(String marca , String modelo , String matricula , int cv , int cc , double precio , int par , Incremento incremento) {
        super(marca , modelo , matricula , cv , cc , precio);
        this.par = par;
        this.incremento = incremento;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Par="+par);
        System.out.println("Incremento= " +incremento);
    }
}
