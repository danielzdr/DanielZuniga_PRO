package controller;

import dto.ProfesorDTO;
import model.Profesor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Gestion {
    private ArrayList<Profesor> listaProfesor= new ArrayList<>();
    private ProfesorDTO profesorDTO;

    public Gestion() {
    }

    public Gestion(ProfesorDTO profesorDTO) {
        this.profesorDTO=profesorDTO;
    }



    public void mostrarProfesores(){
        if (listaProfesor.isEmpty()){
            for (Profesor profesor: listaProfesor){
                System.out.println(profesor);
                profesorDTO.mostrarProfesores();
            }
        }else {
            System.out.println("No hay profesores guardados");
        }
    }
    public void agregarListaLocal(Profesor profesor) {
        listaProfesor.add(profesor);
        profesorDTO.insertarProfesores(profesor);
        System.out.println("Trabajador agregado correctamente");
    }

    public boolean borrarListaLocal(String DNI){

        if (listaProfesor.isEmpty()){
            listaProfesor.remove(DNI);
            System.out.println("Profesor borrado con exito de la lista local");
        }else {
            System.out.println("Profesor no ha sido borrado");
        }
        return false;
    }

    public void exportarFicheroOBJ(){
        File file= new File("src/main/java/resources/profesores.obj");
        ObjectInputStream objectInputStream= null;
        try {
            objectInputStream= new ObjectInputStream(new FileInputStream(file));
            ArrayList<Profesor> listaProfesor = (ArrayList<Profesor>) objectInputStream.readObject();
            for (Profesor profesor : listaProfesor){
                System.out.println(profesor.getNombre()+" "+profesor.getDNI()+" "+profesor.getSalarioAnualBase());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error de entrada/ salida");
            System.out.println(e.getMessage());
        }
    }

}


