package model;

import lombok.Data;

@Data
public class Profesor {
    private String nombre, DNI;
    private int salarioAnualBase;

    public Profesor(String nombre , String DNI , int salarioAnualBase) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.salarioAnualBase = salarioAnualBase;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", salarioAnualBase=" + salarioAnualBase +
                '}';
    }
}
