package model;

import java.util.ArrayList;

public class Cliente {

    private ArrayList<Consumicion> consumiciones;
    private double totalFactura;
    private String nombre;

    public Cliente() {
    }

    public Cliente(ArrayList<Consumicion> consumiciones , double totalFactura , String nombre) {
        this.consumiciones = consumiciones;
        this.totalFactura = totalFactura;
        this.nombre = nombre;
    }

    public ArrayList<Consumicion> getConsumiciones() {
        return consumiciones;
    }

    public void setConsumiciones(ArrayList<Consumicion> consumiciones) {
        this.consumiciones = consumiciones;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    private void incrementarFactura(double precio){
        this.totalFactura+=precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarDatos(){
        System.out.println("nombre" +nombre);
        System.out.println("Total factura "+totalFactura);
        if (consumiciones.isEmpty()){
            System.out.println("Aun no ha pedido nada");
        }else {
            for (Consumicion consumicion: consumiciones){
                consumicion.mostrarDatos();
            }
        }
    }
}
