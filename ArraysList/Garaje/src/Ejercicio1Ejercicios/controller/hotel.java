package Ejercicio1Ejercicios.controller;
import Ejercicio1Ejercicios.Entrada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class hotel {

    private ArrayList<Object[]> listaReservas = new ArrayList<>();
    private final int MAX_HABITACIONES = 20;

    // Constructor
    public hotel() {
        listaReservas = new ArrayList();
    }


    public void registrarReserva(Entrada nuevaReserva) {
        for (Object[] reserva : listaReservas) {
            if (listaReservas.getClass().equals(nuevaReserva.getDniHuesped())) {
                System.out.println("Error: El huésped con DNI " + reserva.getClass() + " ya tiene una reserva.");
                return;
            }
        }


        if (nuevaReserva.getNumeroHabitacion() < 1 && nuevaReserva.getNumeroHabitacion() > MAX_HABITACIONES) {
            System.out.println("Error: El número de habitación debe estar entre 1 y " + MAX_HABITACIONES);
            return;
        }
        System.out.println("Reserva registrada exitosamente.");
    }


    public void cancelarReserva(String dni) {
        for (int i = 0; i < listaReservas.size(); i++) {
            if (listaReservas.get(i).getClass().equals(dni)) {
                listaReservas.remove(i);
                System.out.println("Reserva cancelada correctamente.");
                return;
            }
        }
        System.out.println("Error: No se encontró una reserva con el DNI proporcionado.");
    }


    public void mostrarReservasActuales() {
        if (listaReservas.isEmpty()) {
            System.out.println("No hay reservas actuales.");
        } else {
            for (Object[] reserva : listaReservas) reserva.toString();
        }
    }


    public void mostrarReservasOrdenadas() {
        if (listaReservas.isEmpty()) {
            System.out.println("No hay reservas actuales.");
            return;
        }

        ArrayList<Object[]> reservasOrdenadas = new ArrayList<>();

        Collections.sort(reservasOrdenadas , new Comparator<Object[]>() {
            @Override

            public int compare(Object[] o1, Object[] o2) {
                if (Double.parseDouble(o1[1].toString()) > Double.parseDouble(o2[1].toString()))
                    return 1;
                else if (Double.parseDouble(o1[1].toString()) < Double.parseDouble(o2[1].toString()))
                    return -1;
                else
                    return 0;
            }
        });
        for ( Object[] item : listaReservas) {
            System.out.println( item[0].toString());
        }
        }

    }

