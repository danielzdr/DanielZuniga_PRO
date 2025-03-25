package controller;

import model.Coche;

import java.util.ArrayList;
//Tipo de datos genericos
//O se puede poner extens o implements
public class Gestor <S extends Coche, B, I extends Number>{
    private ArrayList<S> listado;

    public void anadirElemento(S dato){
        this.listado.add(dato);
    }

}
