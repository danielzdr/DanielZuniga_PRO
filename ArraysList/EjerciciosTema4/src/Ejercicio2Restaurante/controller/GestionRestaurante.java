package Ejercicio2Restaurante.controller;
import java.util.ArrayList;
public class GestionRestaurante {
    class GestionRestaurante {
        private int numeroMesa;
        private String nombreCliente;
        private List<String> consumiciones;
        private double precioTotal;

        public Mesa(int numeroMesa, String nombreCliente) {
            this.numeroMesa = numeroMesa;
            this.nombreCliente = nombreCliente;
            this.consumiciones = new ArrayList<>();
            this.precioTotal = 0.0;
        }

        public int getNumeroMesa() {
            return numeroMesa;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public List<String> getConsumiciones() {
            return consumiciones;
        }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void agregarConsumicion(String producto, double precio) {
            consumiciones.add(producto);
            precioTotal += precio;
        }

        public void liberarMesa() {
            consumiciones.clear();
            precioTotal = 0.0;
        }

        @Override
        public String toString() {
            return "Mesa: " + numeroMesa + ", Cliente: " + nombreCliente + ", Consumiciones: " + consumiciones + ", Precio Total: " + precioTotal;
        }
    }
}
