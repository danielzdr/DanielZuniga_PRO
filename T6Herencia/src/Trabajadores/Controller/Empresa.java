package Trabajadores.Controller;

import Trabajadores.model.*;

import java.util.ArrayList;

public class Empresa {
    private ArrayList<Trabajador> trabajadores;
    private Jefe jefe;
    private Asalariados asalariado;
    private Autonomos autonomo;

    public Empresa() {
        trabajadores = new ArrayList<>();
    }

    public void registrarTrabajador(Trabajador trabajador) {
       if (trabajador instanceof Jefe){

       }

    }
    private boolean existeJefe(){
        for (Persona persona: lista Persona){
            if (persona instanceof  Jefe){
                return true;
            }
            return false;
        }
    }


    public void listarTrabajadores(Trabajador trabajador) {
        for (Trabajadores t : trabajadores) {
            if (trabajadores instanceof Jefe ||
                    (tipo.equals("Asalariados") && (t instanceof Asalariados)) ||
                    (tipo.equals("Autonomos") && (t instanceof Autonomos))) {
                t.mostrarDatos();
            }
        }
    }

    public void mostrarDatos(String dni) {
        for (Trabajadores item : trabajadores) {
            if (item.getDni().equals(dni)) {
                item.mostrarDatos();
                return;
            }
        }
        System.out.println("No se encontro ningun dni");
    }

    public boolean despedirTrabajador(String dniJefe, String dniTrabajador) {
        if (jefe == null) {
            System.out.println("No hay jefe en la empresa.");
            return false;
        }

        for (Trabajadores t : trabajadores) {
            if (t.getDni().equals(dni)) {
                jefe.despedir(t);
                trabajadores.remove(t);
                return true;
            }
        }
        System.out.println("No se encontró un trabajador con ese DNI.");
        return false;
    }
}
