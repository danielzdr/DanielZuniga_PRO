package Controller;



import model.Encargo;
import model.Tarea;

import java.util.ArrayList;

public class Planificador {
    private ArrayList<Encargo> listaEncargo= new ArrayList<>();
    private ArrayList<Encargo> listaTarea = new ArrayList<>();

    public Planificador(ArrayList<Encargo> listaTarea) {
        this.listaTarea = listaTarea;
    }

    public Planificador() {
    }

    private Encargo estaEncargo(int id){
        for (Encargo encargo:listaEncargo){
            if (encargo.getId()==id) return encargo;
        }
        return null;
    }

    public void registrarTarea(Encargo encargo) {
        listaTarea.add(encargo);
        System.out.println("Tarea registrada: " + encargo);
    }

    public boolean modificarTarea(int id, String nuevaDescripcion, boolean completado) {
        for (Encargo encargo : listaTarea) {
            if (encargo.getId() == id) {
                encargo.setDescripcion(nuevaDescripcion);
                encargo.setCompleta(completado);
                System.out.println("Tarea modificada con éxito: " + encargo);
                return true;
            }
        }
        return false;
    }

    public void listarTarea(){
        if (listaTarea.isEmpty()){
            System.out.println("La lista esta vacia");
        }else {
            System.out.println("Vamos a nombrar los elementos de la lista ");
            for (Encargo encargo:listaTarea){
                System.out.println(encargo);
            }
        }
    }

        public void completarTarea(){
            for (Encargo encargo: listaEncargo){
                if (!encargo.isCompleta()){
                    System.out.println("Tarea completada correctamente");
                }
            }
        }

    public void listarTareaCompleta() {
        System.out.println("Tareas completas:");
        boolean hayCompletas = false;
        for (Encargo encargo : listaTarea) {
            if (encargo.isCompleta()) {
                System.out.println(encargo);
                hayCompletas = true;
            }
        }
        if (!hayCompletas) {
            System.out.println("No hay tareas completas");
        }
    }


    public void listarTareasIncompletas() {
        System.out.println("Tareas incompletas:");
        boolean hayIncompletas = false;
        for (Encargo encargo : listaTarea) {
            if (!encargo.isCompleta()) {
                System.out.println(encargo);
                hayIncompletas = true;
            }
        }
        if (!hayIncompletas) {
            System.out.println("No hay tareas incompletas");
        }
    }

}

