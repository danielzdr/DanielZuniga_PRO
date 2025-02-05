package Controller;

import java.util.ArrayList;


public class Coche {


    private String marca, modelo, matricula;
    private int cv, km;
    private ArrayList<Coche> Carrera;

    public Coche(String marca, String modelo, String matricula, int km, Coche coche){
        this.marca=marca;
        this.modelo=modelo;
        this.matricula=matricula;
        this.cv=cv;
        this.km=km;
    }

    public void setMarca( int Marca) {this.marca = marca;

    }

    public void setKm(int Km) {
        this.km += km;

    }

    public void setCv() {
        this.cv = cv;
    }

    public String getModelo(){
        return modelo;
    }

    public int getKM(){
        return km;
    }

    public  String getMarca(){
        return  marca;
    }

    public  int getCv(){
        return  cv;
    }

    public String getMatricula(){
        return matricula;
    }



}
