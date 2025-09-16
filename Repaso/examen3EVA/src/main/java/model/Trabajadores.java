package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trabajadores {
    private int id, salario;
    private String nombre, cargo, correo, password;

    public Trabajadores(int salario , String nombre , String cargo , String correo , String password) {
        this.salario = salario;
        this.nombre = nombre;
        this.cargo = cargo;
        this.correo = correo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Trabajadores{" +
                "id=" + id +
                ", salario=" + salario +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
