package model;

import java.util.ArrayList;

public class Centro {
    public ArrayList<Doctor>listaDoctor;
    public ArrayList<Paciente>listaPaciente;

    public Centro() {
    }



    public ArrayList<Doctor> getListaDoctor() {
        return listaDoctor;
    }

    public void setListaDoctor(ArrayList<Doctor> listaDoctor) {
        this.listaDoctor = listaDoctor;
    }

    public ArrayList<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(ArrayList<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }
}
