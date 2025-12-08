package Ejercicio10Llamadas.model;

import java.util.ArrayList;

public class Centralita {

    private ArrayList<LlamadaLocal>listaLlamadasLocales;
    private ArrayList<LlamadaNacional> listaLlamadaNacional;
    private int costeAcumulado;


    public Centralita() {
    }

    public Centralita(ArrayList<LlamadaLocal> listaLlamadasLocales, ArrayList<LlamadaNacional> listaLlamadaNacional , int costeAcumulado) {
        this.listaLlamadasLocales = listaLlamadasLocales;
        this.listaLlamadaNacional = listaLlamadaNacional;
        this.costeAcumulado = costeAcumulado;

    }

    public void agregarNacional(LlamadaNacional llamadaNacional){
        listaLlamadaNacional.add(llamadaNacional);
    }

    public void agregarLocal(LlamadaLocal llamadaLocal){
        listaLlamadasLocales.add(llamadaLocal);
    }

    public void listarLocales(){
        System.out.println("Listado de llamadas locales: ");
        for (LlamadaLocal llamadaLocal: listaLlamadasLocales){
            System.out.println(llamadaLocal);
        }
    }

    public void listarNacional(){
        System.out.println("listado de llamadas nacionales: ");
        for (LlamadaNacional llamadaNacional: listaLlamadaNacional){
            System.out.println(llamadaNacional);
        }
    }

    public void mostrarCostes(){
        System.out.println("El coste acumulado es de : " +costeAcumulado);
    }

    public ArrayList<LlamadaLocal> getListaLlamadasLocales() {
        return listaLlamadasLocales;
    }

    public void setListaLlamadasLocales(ArrayList<LlamadaLocal> listaLlamadasLocales) {
        this.listaLlamadasLocales = listaLlamadasLocales;
    }

    public ArrayList<LlamadaNacional> getListaLlamadaNacional() {
        return listaLlamadaNacional;
    }

    public void setListaLlamadaNacional(ArrayList<LlamadaNacional> listaLlamadaNacional) {
        this.listaLlamadaNacional = listaLlamadaNacional;
    }

    public int getCosteAcumulado() {
        return costeAcumulado;
    }

    public void setCosteAcumulado(int costeAcumulado) {
        this.costeAcumulado = costeAcumulado;
    }
}
