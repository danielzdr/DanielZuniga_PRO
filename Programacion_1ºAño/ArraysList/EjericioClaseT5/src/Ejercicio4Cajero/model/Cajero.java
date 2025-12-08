package Ejercicio4Cajero.model;

import java.util.ArrayList;
import java.util.List;

public class Cajero {
    private List<Banco> bancos;

    public Cajero() {
        bancos = new ArrayList<>();
        bancos.add(new Banco("BBVA", "12345678A"));
        bancos.add(new Banco("Santander", "87654321B"));
    }

    public Banco seleccionarBanco(String nombreBanco) {
        for (Banco banco : bancos) {
            if (banco.getNombre().equals(nombreBanco)) {
                return banco;
            }
        }
        return null;
    }

    public void abrirCuenta(Banco banco, String dni, String pin, double saldoInicial) {
        banco.abrirCuenta(dni, pin, saldoInicial);
    }

    public void cancelarCuenta(Banco banco, String dni, String pin) {
        banco.cancelarCuenta(dni, pin);
    }

    public void ingresarDinero(Banco banco, String dni, String pin, double monto) {
        Cuenta cuenta = banco.buscarCuenta(dni, pin);
        if (cuenta != null) {
            cuenta.ingresarDinero(monto);
        }
    }

    public void sacarDinero(Banco banco, String dni, String pin, double monto) {
        Cuenta cuenta = banco.buscarCuenta(dni, pin);
        if (cuenta != null) {
            cuenta.sacarDinero(monto);
        }
    }

    public String verResumen(Banco banco, String dni, String pin) {
        Cuenta cuenta = banco.buscarCuenta(dni, pin);
        if (cuenta != null) {
            return cuenta.verResumen();
        }
        return "Cuenta no encontrada";
    }
}