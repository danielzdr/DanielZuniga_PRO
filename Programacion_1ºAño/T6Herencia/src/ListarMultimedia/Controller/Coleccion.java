package ListarMultimedia.Controller;

import ListarMultimedia.model.Audio;
import ListarMultimedia.model.Elemento;
import ListarMultimedia.model.Libro;
import ListarMultimedia.model.Video;

import java.util.ArrayList;
import java.util.Scanner;

public class Coleccion {

    private ArrayList<Elemento> elementos;


    public Coleccion() {
        this.elementos = new ArrayList<>();
    }

    public void añadirElemento(Libro item) {
        elementos.add(item);

    }

    public void eliminarElemento(String id) {
        for (Elemento elemento : elementos) {
            if (elemento.getId().equalsIgnoreCase(id)) {
                elementos.remove(elemento);
            }
        }
    }

    public void buscarElemento(String autor , String director , String actor) {
    }

    public void listarElementos(Audio audio , Video video) {
    }

    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elemento> elementos) {
        this.elementos = elementos;
    }

    public void listarElementos(Scanner scanner) {
    }



}
