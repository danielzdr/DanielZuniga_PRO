package org.example.formulario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String nombre,correo,apellidos,genero;
    private int edad;
    private boolean disponibilidad;

    @Override
    public String toString(){
        return nombre;
    }


}
