package model;

import java.util.List;

public class TareaPersonal extends Tarea{

    private String ubicacion;

    public TareaPersonal(List<String> responsables) {
        super(responsables);
    }

    @Override
    public void enviarRecordatorio() {
        //se imprime por consola a cada uno de los responsables el mensaje
        //Borja, recuerda terminar las tareas asociadas a esta tarea
        //Luis, recuerda terminar las tareas asociadas a esta tarea
        for (String responsable: responsables){
            System.out.println(responsable + " : Recuerda terminar las tareas 2");
        }


    }

    public TareaPersonal(){}

    public TareaPersonal(String ubicacion){
        this.ubicacion=ubicacion;

    }

    public TareaPersonal(String titulo , String descripcion , boolean prioritario , int numeroPersonas , String ubicacion) {
        super(titulo , descripcion , prioritario , numeroPersonas);
        this.ubicacion = ubicacion;
    }

    public TareaPersonal(String titulo , String descripcion , int numeroPersonas , String ubicacion) {
        super(titulo , descripcion , numeroPersonas);
        this.ubicacion = ubicacion;
    }

    public void mostrarDatos(){
        //super.Para llamar a cualquier metodo desde la clase padre
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return super.toString()+"TareaPersonal{" +
                "ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
