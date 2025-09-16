package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Productos {
    private int precio, cantidad;
    private String nombre;

    public Productos(int precio,String nombre) {
        this.precio = precio;
        this.nombre= nombre;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "precio=" + precio +
                ", cantidad=" + cantidad +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
