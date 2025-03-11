package controller;

import java.util.ArrayList;
//Tipo de datos genericos
public class Gestor <S>{
    private ArrayList<S> listado;

    public void anadirElemento(S dato){
        this.listado.add(dato);
    }
}
