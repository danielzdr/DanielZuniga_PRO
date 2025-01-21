package Ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;

public class Controladora1 {

    private ArrayList<Object[]> listaProductos;

    public Controladora1(){
        listaProductos=new ArrayList<>();
    }

    public void agregarProducto(String nombre, int precio){
        Object[] producto= new Object[]{nombre,precio};
        listaProductos.add(producto);
    }

    public void listarProductos(){
        for (Object[] item: listaProductos){
            System.out.println("El nombre es "+item[0]+ ", el precio es "+item[1]);
        }
    }

    public void listarPrecioMinimo(int precio){
        for (Object[] item: listaProductos){
            if ((int)item[1]>= precio){
                System.out.println("El nombre es "+item[0]+", el precio es "+item[1]);
            }
        }
    }

    public void listarOrdenado(){
        listaProductos.sort(new Comparator<Object[]>() {
            //sobreescribir-> override
            @Override
            public int compare(Object[] o1 , Object[] o2) {
                //condicion de ordenacion si quiero cambiar el orden cambio el return 1 por -1 y -1 por 1
                if ((int)o1[0] > (int)o2[1]){
                    return 1;
                    //casteo los object array a int
                } else if ((int)o1[0] < (int)o2[1]) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        //listar ordenados siempre se llaman desde el metodo internamente
        listarProductos();
    }


    }

