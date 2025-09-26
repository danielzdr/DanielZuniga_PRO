package model;

import java.util.Date;

public class TareasTrabajo extends Tarea{
    private String proyecto;

    public TareasTrabajo() {
    }

    public TareasTrabajo(String proyecto) {
        this.proyecto = proyecto;
    }

    public TareasTrabajo(int id , String titulo , String descripcion , Date date , boolean completada , String proyecto) {
        super(id , titulo , descripcion , date , completada);
        this.proyecto = proyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return super.toString()+ "TareasTrabajo{" +
                "proyecto='" + proyecto + '\'' +
                '}';
    }
}
