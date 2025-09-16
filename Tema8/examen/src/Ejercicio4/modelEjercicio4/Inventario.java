package Ejercicio4.modelEjercicio4;

import java.util.ArrayList;

public class Inventario<T> {
    private ArrayList<T> listaInventario;

    public Inventario() {
    }

    public Inventario(ArrayList<T> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public ArrayList<T> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(ArrayList<T> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public void agregarElemento(T t) throws NullPointerException{
        for (T product: listaInventario ) {
            listaInventario.add(product);
            System.out.println("Producto agregado correctamente");
        }
    }

    public void mostrarInventario(){
        for (T producto:listaInventario){
            System.out.println(producto.toString());
        }
    }

    public class Producto{
        private String nombre;
        private double precio;
        private int stock;

        public Producto() {
        }

        public Producto(String nombre , double precio , int stock) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return "Producto{" +
                    "nombre='" + nombre + '\'' +
                    ", precio=" + precio +
                    ", stock=" + stock +
                    '}';
        }
    }


}
