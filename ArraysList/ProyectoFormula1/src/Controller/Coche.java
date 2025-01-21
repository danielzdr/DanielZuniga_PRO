package Controller;

import java.util.ArrayList;

public class Coche {


    private String marca, modelo, matricula;
    private int cv, km;
    private ArrayList<Coche> listaCoches;


    public Coche(String marca, String modelo, String matricula, int km, int cv){
        this.marca=marca;
        this.modelo=modelo;
        this.matricula=matricula;
        this.km=km;
        this.cv=cv;
    }

    public void setMarca() {
        this.marca = marca;

    }

    public void setKm() {
        this.km += km;

    }

    public void setCv() {
        this.cv = cv;
    }


}
