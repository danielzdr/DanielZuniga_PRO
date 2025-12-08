package Ejercicio1.model;

public class Motor {

    private int aceite , CV;

    public Motor(int CV, int aceite){
        this.CV=CV;
        this.aceite=0;

    }

    public int getAceite() {
        return aceite;
    }

    public int getCV() {
        return CV;
    }

    public void setAceite(int aceite) {
        this.aceite = aceite;
    }

    public void setCV(int CV) {
        this.CV = CV;
    }
}
