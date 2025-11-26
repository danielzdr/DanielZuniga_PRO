package org.example.gestionpizzeria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Pedidos {
    private String nombre;
    private int  telefono;
    private String tipoPizza,tamanio;
    private int precio;
    private boolean servido=false;

    public Pedidos(String nombre , int telefono , String tipoPizza , String tamanio) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoPizza = tipoPizza;
        this.tamanio = tamanio;
        this.precio=calcularPrecio();
    }

    private int calcularPrecio() {
        return switch (nombre) {
            case "Barbacoa" -> switch (tamanio) {
                case "Pequeña" -> 7;
                case "Mediana" -> 12;
                default -> 15;
            };
            case "Hawaiana" -> switch (tamanio) {
                case "Pequeña" -> 5;
                case "Mediana" -> 10;
                default -> 13;
            };
            case "Jamon y queso" -> switch (tamanio) {
                case "Pequeña" -> 4;
                case "Mediana" -> 8;
                default -> 10;
            };
            case "4 quesos" -> switch (tamanio) {
                case "Pequeña" -> 8;
                case "Mediana" -> 13;
                default -> 17;
            };
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", tipoPizza='" + tipoPizza + '\'' +
                ", tamanio='" + tamanio + '\'' +
                ", precio=" + precio +
                ", servido=" + servido +
                '}';
    }

    public boolean isServido() {
        return servido;
    }

    public void setServido(boolean servido) {
        this.servido = servido;
    }
}
