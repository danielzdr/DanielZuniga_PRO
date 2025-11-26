package org.example.examenjavafx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios {
    private int id;
    private String nombre,correo,password;

}
