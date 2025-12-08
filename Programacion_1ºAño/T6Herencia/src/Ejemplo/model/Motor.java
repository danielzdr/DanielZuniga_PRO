package Ejemplo.model;

public class Motor extends Vehiculo {
    private int cv;
    private int aceite;

    public Motor(Motor motor , String bastidor , String empuniadura) {
        super(motor , bastidor);
        this.empuniadura = empuniadura;
    }
    public Motor(int cv, int aceite) {
        this.cv = cv;
        this.aceite = aceite;
    }



    public Motor() {

    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
    }

    private String empuniadura;



    public int getCv() {
        return cv;
    }

    public int getAceite() {
        return aceite;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public void setAceite(int aceite) {
        this.aceite = aceite;
    }
}
