package Ejercicio1Hotel.controller;

public class ReservaHotel {
    private int numeroHabitacion;
    private String nombreHuesped;
    private String telefonoHuesped;
    private String dniHuesped;
    private int numeroPersonas;

    public ReservaHotel(int numeroHabitacion, String nombreHuesped, String telefonoHuesped, String dniHuesped, int numeroPersonas) {
        this.numeroHabitacion = numeroHabitacion;
        this.nombreHuesped = nombreHuesped;
        this.telefonoHuesped = telefonoHuesped;
        this.dniHuesped = dniHuesped;
        this.numeroPersonas = numeroPersonas;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public String getDniHuesped() {
        return dniHuesped;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    @Override
    public String toString() {
        return "Habitación: " + numeroHabitacion + ", Nombre: " + nombreHuesped + ", DNI: " + dniHuesped + ", Teléfono: " + telefonoHuesped + ", Personas: " + numeroPersonas;
    }
}

