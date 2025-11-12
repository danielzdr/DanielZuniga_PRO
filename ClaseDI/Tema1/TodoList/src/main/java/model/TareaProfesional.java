package model;

import java.util.Date;
import java.util.List;

public  class TareaProfesional extends Tarea{

    private int presupuesto;
    private Date fechaLiminte;

    public TareaProfesional(List<String> responsables) {
        super(responsables);
    }

    @Override
    public void enviarRecordatorio() {
        for (String responsable: responsables){
            System.out.println(responsable + " : Recuerda terminar la tarea 1");
        }
    }

    public TareaProfesional(){}

    public TareaProfesional(int presupuesto, Date fechaLimite){
        this.presupuesto=presupuesto;
        this.fechaLiminte=fechaLimite;
    }

    public TareaProfesional(String titulo , String descripcion , int numeroPersonas , int presupuesto , Date fechaLiminte) {
        super(titulo , descripcion , numeroPersonas);
        this.presupuesto = presupuesto;
        this.fechaLiminte = fechaLiminte;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Date getFechaLiminte() {
        return fechaLiminte;
    }

    public void setFechaLiminte(Date fechaLiminte) {
        this.fechaLiminte = fechaLiminte;
    }

    @Override
    public String toString() {
        return super.toString()+"TareaProfesional{" +
                "presupuesto=" + presupuesto +
                ", fechaLiminte=" + fechaLiminte +
                '}';
    }
}
