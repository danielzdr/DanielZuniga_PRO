package ListarMultimedia.model;

import java.util.ArrayList;

public class Video extends Elemento{
    private Persona director;
    private ArrayList<Persona> actores ;

    public Video() {

    }

    public Video(String id , String titulo , String formato , Persona autor  , int tamanio , Persona director ) {
        super(id , titulo , formato , autor , tamanio);
        this.director = director;
        this.actores = actores;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Director = " +director);
        System.out.println("Actores =" +actores);
        super.mostrarDatos();
    }

    public Persona getDirector() {
        return director;
    }

    public void setDirector(Persona director) {
        this.director = director;
    }

    public ArrayList<Persona> getActores() {
        return actores;
    }

    public void setActores(ArrayList<Persona> actores) {
        this.actores = actores;
    }
}
