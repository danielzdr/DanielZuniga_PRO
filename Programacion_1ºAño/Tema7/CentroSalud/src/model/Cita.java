package model;

public class Cita {
    public String fecha;
    public Especialidad especialidad;
    public Doctor doctor;
    public Paciente paciente;

    public Cita(String fecha, Especialidad especialidad, Doctor doctor, Paciente paciente) {
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita con " + doctor + " para " + paciente + " en " + fecha;
    }
}
