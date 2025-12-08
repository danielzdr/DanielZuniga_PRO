package Ejercicio4;

import Ejercicio4.modelEjercicio4.Inventario;

public class Entrada {
    public static void main(String[] args)  {
        try {

        }catch (NullPointerException e) {
            Inventario<Inventario.Producto> inventario = new Inventario<>();
            Inventario.Producto producto = new inventario.Producto("Pollo" , 100.50 , 5);
            Inventario.Producto producto2 = new inventario.Producto("Pescado" , 10.70 , 10);
            Inventario.Producto producto3 = new inventario.Producto("Arroz" , 4.25 , 9);

            inventario.agregarElemento(producto);
            inventario.agregarElemento(producto2);
            inventario.agregarElemento(producto3);

            inventario.mostrarInventario();
            System.out.println("Error de inicializacion");
        }finally {
            System.out.println("Error de acabado");
        }


    }
}
