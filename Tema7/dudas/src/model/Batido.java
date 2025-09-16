package model;

import utils.Sabores;

public class Batido {
    private Sabores sabor;
    private int precio;

    public Batido() {
    }

    public Batido(Sabores sabor , int precio) {
        this.sabor = sabor;
        this.precio = precio;
    }

}
