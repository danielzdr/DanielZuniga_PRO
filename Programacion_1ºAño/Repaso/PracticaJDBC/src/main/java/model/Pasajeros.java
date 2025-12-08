package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pasajeros {
    private int id,edad,peso;
    private String nombre;

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nombre: " + nombre +
                ", Edad: " + edad +
                ", Peso: " + peso ;
    }
}

