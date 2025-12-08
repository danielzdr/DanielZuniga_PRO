package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coche {
    private int id;
    private String matricula, modelo,marca,color;
    private  static float serialVersionUID = 1L;
    @Override
    public String toString() {
        return "ID: " + id +
                ", Matrícula: " + matricula +
                ", Marca: " + marca +
                ", Modelo: " + modelo +
                ", Color: " + color;
    }

    public String toCSV() {
        return id + ";" + matricula + ";" + marca + ";" + modelo + ";" + color;
    }
}
