package Ejercicio4Cajero.model;

public class Cuenta {
    private String dni;
    private String pin;
    private double saldo;
    private int isbn;

    public Cuenta(String dni, String pin, double saldo, int isbn) {
        this.dni = dni;
        this.pin = pin;
        this.saldo = saldo;
        this.isbn = isbn;
    }

    public void ingresarDinero(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public void sacarDinero(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
        }
    }

    public String verResumen() {
        return "DNI: " + dni + ", Saldo: " + saldo + ", ISBN: " + isbn;
    }

    public boolean esValida(String dni, String pin) {
        return this.dni.equals(dni) && this.pin.equals(pin);
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getIsbn() {
        return isbn;
    }
}
