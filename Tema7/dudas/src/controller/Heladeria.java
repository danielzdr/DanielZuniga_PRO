package controller;

import java.util.ArrayList;

public class Heladeria <T>{
    private ArrayList <T> catalogoHelado;
    private ArrayList <T> pedidos;

    public Heladeria(ArrayList<T> pedidos) {
        catalogoHelado=new ArrayList();
        this.pedidos = pedidos;
    }

    public void realizarPedido(T producto){
        pedidos.add(producto);
    }

    
}
