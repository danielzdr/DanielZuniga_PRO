package model;

import java.util.Date;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private Date date;
    private boolean completada;
    protected Prioridad prioridad;

    public Tarea() {
    }

    public Tarea(int id , String titulo , String descripcion , Date date , boolean completada, Prioridad prioridad) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.date = date;
        this.completada = completada;
        this.prioridad=prioridad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", date=" + date +
                ", completada=" + completada +
                '}';
    }
}
