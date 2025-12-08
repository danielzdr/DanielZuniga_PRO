package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int id;
    private String nombre, apellido, correo, pass;
    private int perfil, ventas;

    public Usuario(String nombre , String apellido , String correo , String pass , int perfil , int ventas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
        this.perfil = perfil;
        this.ventas = ventas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPass() {
        return pass;
    }

    public int getPerfil() {
        return perfil;
    }

    public int getVentas() {
        return ventas;
    }
}
