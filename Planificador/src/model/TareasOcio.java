package model;

import java.util.ArrayList;
import java.util.Date;

public class TareasOcio extends Tarea{
private String ubicacion;
private ArrayList<String> subtareas;

    public TareasOcio() {
    }

    public TareasOcio(String ubicacion , ArrayList<String> subtareas) {
        this.ubicacion = ubicacion;
        this.subtareas = subtareas;
    }

    public TareasOcio(int id , String titulo , String descripcion , Date date , boolean completada , Prioridad prioridad, String ubicacion , ArrayList<String> subtareas) {
        super(id , titulo , descripcion , date , completada,prioridad);
        this.ubicacion = ubicacion;
        this.subtareas = subtareas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<String> getSubtareas() {
        return subtareas;
    }

    public void setSubtareas(ArrayList<String> subtareas) {
        this.subtareas = subtareas;
    }

    @Override
    public String toString() {
        return super.toString()+ "TareasOcio{" +
                "ubicacion='" + ubicacion + '\'' +
                ", subtareas=" + subtareas +
                '}';
    }
}
