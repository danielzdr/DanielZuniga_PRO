package Trabajadores.Controller;

import Trabajadores.model.Asalariados;
import Trabajadores.model.Autonomos;
import Trabajadores.model.Jefe;
import Trabajadores.model.Trabajadores;

import java.util.ArrayList;

public class Empresa {
    private ArrayList<Trabajadores> trabajadores;
    private Jefe jefe;
    private Asalariados asalariado;
    private Autonomos autonomo;

    public Empresa() {
        trabajadores = new ArrayList<>();
    }

    public boolean registrarTrabajador(Trabajadores trabajador) {
        for (Trabajadores t : trabajadores) {
            if (t.getDni().equals(trabajador.getDni())) {
                System.out.println("Error: Ya existe un trabajador con el mismo dni");
                return false;
            }
        }
        if (trabajador instanceof Jefe && jefe != null) {
            System.out.println("Error: Solo puede haber un jefe");
            return false;
        }

        if (trabajador instanceof Jefe) {
            jefe = (Jefe) trabajador;
        }
        trabajadores.add(trabajador);
        System.out.println("Trabajador agregado con exito");
        return true;

    }

    public void listarTrabajadores(String tipo) {
        for (Trabajadores t : trabajadores) {
            if (tipo.equals("todos") ||
                    (tipo.equals("asalariados") && t instanceof Asalariados) ||
                    (tipo.equals("autonomos") && t instanceof Autonomos)) {
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

    public boolean despedirTrabajador(String dni) {
        if (jefe == null) {
            System.out.println("No hay jefe en la empresa.");
            return false;
        }

        for (Trabajadores item : trabajadores) {
            if (item.getDni().equals(dni)) {
                jefe.despedir(item);
                trabajadores.remove(item);
                return true;
            }
        }
        System.out.println("No se encontró un trabajador con ese DNI.");
        return false;
    }
}
