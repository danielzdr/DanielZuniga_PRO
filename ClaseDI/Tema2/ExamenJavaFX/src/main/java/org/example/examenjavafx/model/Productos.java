package org.example.examenjavafx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {
    private int id;
    private String nombre,categoria;
    private int precio;
    private String descripcion;
}
