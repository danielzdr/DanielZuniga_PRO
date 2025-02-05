package Ejercicio7Equipo.model;

import java.util.ArrayList;
import java.util.Random;

public class Equipo {

    private String nombre;
    private int nivelAtaque, nivelMedio, nivelDefensa;
    private int goles;
    private ArrayList<Jugador> plantilla;
    Random random=new Random();

    public Equipo(String nombre){
        this.nombre=nombre;
        this.nivelAtaque=(int)Math.random()*100+1;
        this.nivelMedio=(int)Math.random()*100+1;
        this.nivelDefensa=(int)Math.random()*100+1;
        this.plantilla=new ArrayList<>();
    }

    public Equipo (String nombre, int nivelAtaque, int nivelMedio, int nivelDefensa){
        this.nombre=nombre;
        this.nivelAtaque=nivelAtaque;
        this.nivelMedio=nivelMedio;
        this.nivelDefensa=nivelDefensa;
        this.plantilla=new ArrayList<>();
    }

    public boolean atacar(){
        //Un método atacar que no reciba nada por parámetros y retorne un booleano. La funcionalidad será la siguiente
    // Retornará true (se ha metido gol) si el resultado de la operación de (nivelAtaque * (aleatorio entre 0-1) + (nivelCentro * aleatorio entre 0-1)/2) es superior a 90. Además sumará uno a la variable goles
    // Retornará false en el resto de los casos

        double resultado = (nivelAtaque * random.nextDouble()*1 + (nivelMedio * random.nextDouble()*1) / 2);
        System.out.println("El resultado del ataque es "+resultado);
        if (resultado > 85) {
            goles++;
            return true;
        }
            return false;

    }


    public void listarDelantero(){
        for (Jugador item: plantilla){
            if (item.isEstrella()){
            item.mostrarDatos();}
        }
        }

    public void ficharJugador(Jugador jugador){
        this.plantilla.add(jugador);
    }

    public void mostrarDatos(){
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Ataque: "+this.nivelAtaque);
        System.out.println("Medio: "+this.nivelMedio);
        System.out.println("Defensa: "+this.nivelDefensa);
        System.out.println("Goles: "+this.goles);

    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public int getNivelMedio() {
        return nivelMedio;
    }

    public int getNivelDefensa() {
        return nivelDefensa;
    }

    public int getGoles() {
        return goles;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivelAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

    public void setNivelMedio(int nivelMedio) {
        this.nivelMedio = nivelMedio;
    }

    public void setNivelDefensa(int nivelDefensa) {
        this.nivelDefensa = nivelDefensa;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public String toString(){
        return nombre;
    }
}
