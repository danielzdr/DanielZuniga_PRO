package model;

import java.util.ArrayList;

public class Persona {
    private String dni, nombre, apellido;
    private ArrayList <Persona> listaPersona;

    public Persona(String dni, String nombre, String apellido){
        listaPersona=new ArrayList<>();
        this.dni=dni;
        this.nombre=nombre;
        this.apellido=apellido;
    }

    public void mostrarDatos(){
        System.out.println("El dni es "+this.dni);
        System.out.println("El nombre es "+this.nombre);
        System.out.println("El apellido es "+this.apellido);

    }
}
