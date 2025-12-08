package org.example.login.model;


import java.io.Serializable;

public class Persona implements Serializable {
    public String nombre;
    public int edad;
    public String email;

    public Persona(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Email: " + email;
    }
}

