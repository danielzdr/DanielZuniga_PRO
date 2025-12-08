package Ejercicio8Consumicion.model;

import java.util.ArrayList;

public class Entrada {

    private ArrayList<Cuenta> cuentas;

    public Entrada() {
        this.cuentas = new ArrayList<>();
    }

    public void registrarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta obtenerCuenta(int identificador) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getIdentificador() == identificador) {
                return cuenta;
            }
        }
        return null;
    }

    public void mostrarValorCuenta(int identificador) {
        Cuenta cuenta = obtenerCuenta(identificador);
        if (cuenta != null) {
            System.out.println("Valor total de la cuenta " + identificador + ": " + cuenta.obtenerTotal());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void cambiarEstadoCuenta(int identificador, boolean estado) {
        Cuenta cuenta = obtenerCuenta(identificador);
        if (cuenta != null) {
            cuenta.setEstado(estado);
            System.out.println("El estado de la cuenta " + identificador + " ha cambiado a " + (estado ? "Pagada" : "No pagada"));
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}
