package Ejercicio7Equipo.model;

public class Jugador {
    private String nombre, posicion;
    private boolean estrella;
    private int calidad;

    public Jugador(){}

    public  Jugador(String nombre, String posicion, int calidad){
        this.nombre=nombre;
        this.posicion=posicion;
        this.calidad=calidad;
        if (calidad >=90){
            estrella=true;
        }else {
            estrella=false;
        }
    }

    public void mostrarDatos(){
        System.out.println("Nombre "+nombre);
        System.out.println("Posicion "+posicion);
        System.out.println("Estrella "+estrella);
        System.out.println("Calidad "+calidad);
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean isEstrella() {
        return estrella;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setEstrella(boolean estrella) {
        this.estrella = estrella;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }
}
