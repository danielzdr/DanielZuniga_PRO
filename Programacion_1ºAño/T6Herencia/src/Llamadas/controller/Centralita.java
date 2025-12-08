package Llamadas.controller;

import Llamadas.model.Llamada;

import java.util.ArrayList;

public class Centralita {

    private ArrayList<Llamada> llamadas;  // Lista de llamadas registradas

    public Centralita() {
        this.llamadas = new ArrayList<>();
    }


    public void registrarLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }


    public void mostrarLlamadas() {
        for (Llamada llamada : llamadas) {
            llamada.mostrarLlamada();
        }
    }


    public void mostrarCosteTotal() {
        double costeTotal = 0;
        for (Llamada llamada : llamadas) {
            costeTotal += llamada.getCoste();
        }
        System.out.println("Coste total de todas las llamadas: " + costeTotal + "€");
    }

}
