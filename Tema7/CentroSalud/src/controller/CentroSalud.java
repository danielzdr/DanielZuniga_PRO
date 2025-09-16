package controller;

import model.Cita;
import model.Doctor;
import model.Especialidad;
import model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class CentroSalud <T extends Doctor>{
    List<Paciente> pacientes;
    List<T> doctores;
    List<Cita> citas;

    public CentroSalud() {
        this.pacientes = new ArrayList<>();
        this.doctores = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public void admitirPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void contratarDoctor(T doctor) {
        doctores.add(doctor);
    }

    public void verDoctores() {
        for (T doctor : doctores) {
            System.out.println(doctor);
        }
    }

    public void verPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    public void verCitasPaciente(Paciente paciente) {
        for (Cita cita : citas) {
            if (cita.paciente.equals(paciente)) {
                System.out.println(cita);
            }
        }
    }

    public void verCitasDoctor(T doctor) {
        for (Cita cita : citas) {
            if (cita.doctor.equals(doctor)) {
                System.out.println(cita);
            }
        }
    }

    public void pedirCita(String nss, Especialidad especialidad, String fecha) {
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (p.equals(nss)) {
                paciente = p;
                break;
            }
        }

        if (paciente == null) {
            System.out.println("No se encontró paciente con NSS " + nss);
            return;
        }

        T doctor = null;
        for (T d : doctores) {
            if (d.especialidad == especialidad) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("No hay doctores disponibles para la especialidad " + especialidad);
            return;
        }

        Cita cita = new Cita(fecha, especialidad, doctor, paciente);
        citas.add(cita);
        System.out.println("Cita agendada para " + paciente + " con " + doctor + " el " + fecha);
    }
}

