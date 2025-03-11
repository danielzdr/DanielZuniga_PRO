package controller;


import model.Bici;
import model.Patinete;
import model.Vehiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {
        private ArrayList<Vehiculo> stock;
        private double caja;



        public Tienda() {
            this.stock = new ArrayList<>();
            this.caja = 0;
        }



        public void agregarVehiculo(Scanner scanner , Vehiculo vehiculo ) {
            if (buscarPorNumeroSerie(vehiculo.getnumeroSerie())==null) {
                stock.add(vehiculo);
            }else {
                System.out.println("Ya existe el numero de serie");
            }


        }

        private Vehiculo buscarPorNumeroSerie(String numeroSerie) {
            for (Vehiculo vehiculo : stock) {
                if (vehiculo.getnumeroSerie().equalsIgnoreCase(numeroSerie)) {
                    return vehiculo;
                }
            }
            System.out.println("Vehículo no encontrado.");
            return null;
        }

        private void mostrarDatos(String numeroSerie){
            if (buscarPorNumeroSerie(numeroSerie)!=null){
                buscarPorNumeroSerie(numeroSerie).mostrarDatos();
            }else{

                System.out.println("No puedo, vehiculo no disponible");
            }
        }


        public void venderVehiculo(Scanner scanner, String numeroSerie) {
            Vehiculo vehiculo = buscarPorNumeroSerie(numeroSerie);
            if (vehiculo != null) {
                vehiculo.vender();
                stock.remove(vehiculo);
            }
        }


        public void repararVehiculo(Scanner scanner , Vehiculo vehiculo) {
           if (vehiculo instanceof Bici ) {
               vehiculo.setPrecioVenta(vehiculo.getPrecioVenta()+(vehiculo.getPrecioVenta()*0.05));
               stock.add(vehiculo);
           } else if (vehiculo instanceof Patinete) {
               vehiculo.setPrecioVenta(vehiculo.getPrecioVenta()+(vehiculo.getPrecioVenta()*0.1));
               stock.add(vehiculo);
           }else {
               System.out.println("No tenemos esa funcionalidad");
           }

        }
        }


