package Ejercicio4Cajero.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Banco {
    private String nombre;
    private String cif;
    private List<Cuenta> cuentas;

    public Banco(String nombre, String cif) {
        this.nombre = nombre;
        this.cif = cif;
        this.cuentas = new ArrayList<>();
    }

    public void abrirCuenta(String dni, String pin, double saldoInicial) {
        Random rand = new Random();
        int isbn = rand.nextInt(10000) + 1; // ISBN aleatorio entre 1 y 10000
        Cuenta nuevaCuenta = new Cuenta(dni, pin, saldoInicial, isbn);
        cuentas.add(nuevaCuenta);
    }

    public void cancelarCuenta(String dni, String pin) {
        Cuenta cuenta = buscarCuenta(dni, pin);
        if (cuenta != null) {
            cuentas.remove(cuenta);
        }
    }

    public Cuenta buscarCuenta(String dni, String pin) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.esValida(dni, pin)) {
                return cuenta;
            }
        }
        return null;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
}