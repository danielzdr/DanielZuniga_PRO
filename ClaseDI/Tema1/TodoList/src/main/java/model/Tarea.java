package model;

import java.util.ArrayList;
import java.util.List;

abstract public class Tarea {
    //variables
    //constructores
    //metodos ->getter y setter
    //Una tarea tiene asociada un numero de personas
    //cuando se crea la tarea es necesario pedir cuantas personas (no que personas)
    //encargan de la tarea
    //No podras agregar dos personas que tengan el mismo dni

    private String titulo, descripcion;
    private boolean prioritario, completada;
    private Persona [] encargados;
    private int cantidadEncargados;
    private ArrayList<Encargo> listaTareas;
    protected List<String> responsables;

    public Tarea(List<String> responsables) {
        this.responsables = responsables;
    }

    public Tarea() {
    }


    //crear el metodo que permite agregar un encargo y poner avisos
    //los encargos tener ID unico
    //crear el metodo que permite eliminar y poner avisos

    abstract public void enviarRecordatorio();




    public Tarea(String titulo , String descripcion , boolean prioritario,int numeroPersonas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioritario = prioritario;
        encargados= new Persona[numeroPersonas];
        listaTareas= new ArrayList<>();
        //completada = false;
    }

    public Tarea(String titulo , String descripcion ,int numeroPersonas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        encargados= new Persona[numeroPersonas];
        //completada = false;
    }

    private Encargo estaEncargo(int id){
        for (Encargo encargo:listaTareas){
            if (encargo.getId()==id) return encargo;
        }
        return null;
        }


    public void agregarEncargo(Encargo encargo){
        if (estaEncargo(encargo.getId())!=null){
            System.out.println("No se puede agregar");
        }else {
            listaTareas.add(encargo);
            System.out.println("Si se puede agregar");
        }
    }

    public void eliminarEncargo(int id){
        if (estaEncargo(id)!=null){
            listaTareas.remove(estaEncargo(id));
            System.out.println("Borrado correctamente");
        }else {
            System.out.println("Error, el id no esta en la lista de agregados");
        }
    }

    /*
Listar todos los encargos de una tarea
 */
    public void listarEncargos(ArrayList<Encargo> listaTareas){
        if (listaTareas.isEmpty()){
            System.out.println("La lista esta vacia");
        }else {
            System.out.println("Elementos de la lista");
            for (Encargo encargo: listaTareas){
                System.out.println(encargo);
            }
        }
    }

/*
Buscar un encargo por id y mostrar sus datos
 */

public void buscarEncargo(int id){
    for (Encargo encargo:listaTareas){
        if (encargo.getId() == id ){
            System.out.println("Buscando elementos");
        }else {
            System.out.println("No se ha encontrado ningun elemento");
        }
    }
    estaEncargo(id).mostrarDatos();
}

/*
Completar un encargo -> pasar su variable completada a true
 */

    public void completarEncargo(Encargo encargo) {
        encargo.completar();
    }


    public boolean isEncargoCompletado(Encargo encargo) {
        return encargo.isCompleta();
    }

/*
Mostrar un encargo que estan completados
 */
    public void mostrarEncargosCompletos(){
        System.out.println("Encargos completados: ");
        for (Encargo encargo: listaTareas){
            if (encargo.isCompleta()){
                System.out.println(encargo.setCompleta(true));
            }
        }
    }

/*
Completar una tarea -> Una tarea quedará completa si todos sus encargos
estan completos
 */
    public void completarTarea(){
        for (Encargo encargo: listaTareas){
            if (!encargo.isCompleta()){

            }
        }
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

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public void asignarResponsable(Persona persona){
        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i]==null){
                encargados[i]=persona;
                System.out.println("Persona agregada correctamente");
                return;
            }
        }
        System.out.println("No hay hueco disponible, tarea completa");
    }

    //En una tarea se pueden quitar responsables solo podre quitar unatarea si el dni
    //que me indicas esta dentro de la lista de resppnsables.Mostrar aviso tanto para procesos ok como para precesos no OK


    public void eliminarResponsable(String dni){
        for (Persona persona: encargados){
            if (persona!=null && persona.getDni().equalsIgnoreCase(dni)){
                persona=null;
                System.out.println("Persona eliminada correctamente");
                return;
            }
        }
        System.out.printf("La persona con dni %s no esta en esta tarea %n",dni);
    }

    //mostrar los datos de todos los usuarios que son responsables de dichas tareas
    //en caso de no tener ninguna avisar
    //en caso de tener huecos disponibles, avisar de cuantos

    //Corregir mostrarDatos
    public void mostrarDatos(String nombre,String apellido, String dni){
        for (Persona persona : encargados){
            if (persona!=null && persona.getNombre().equalsIgnoreCase(nombre) && persona.getApellido().equalsIgnoreCase(apellido)
             && persona.getDni().equalsIgnoreCase(dni)){
                System.out.println("Las personas tienen huecos disponibles");
            return;
            }

        }
        System.out.println("No hay huecos disponibles");
    }

    public void asignarResponsableDNI(Persona persona) {

        for (int i = 0; i < encargados.length; i++) {
            if (encargados[i].getDni().equals(persona.getDni())) {
                System.out.println("No se puede agregar: el DNI ya existe.");
                return;
            }
        }


        if (cantidadEncargados < encargados.length) {
            encargados[cantidadEncargados] = persona;
            cantidadEncargados++;
            System.out.println("Agregado correctamente.");
        } else {
            System.out.println("No se puede agregar: no hay espacio.");
        }
    }



    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", prioritario=" + prioritario +
                ", completada=" + completada +
                '}';
    }
}
