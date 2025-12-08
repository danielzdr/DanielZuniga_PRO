package controller;

import DTO.ProductosDTO;
import DTO.TrabajadoresDTO;
import model.Productos;
import model.Trabajadores;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Trabajadores> listaTrabajadores = new ArrayList<>();
    private TrabajadoresDTO trabajadoresDTO;
    private ArrayList<Productos> listaProductos = new ArrayList<>();
    private ProductosDTO productosDTO;

    public Tienda(TrabajadoresDTO trabajadoresDTO) {
        this.trabajadoresDTO = trabajadoresDTO;
    }

    public Tienda(ProductosDTO productosDTO){
        this.productosDTO= productosDTO;
    }


    public void agregarTrabajador(Trabajadores trabajadores) {
                listaTrabajadores.add(trabajadores);
                trabajadoresDTO.crearUsuarios(trabajadores);
                System.out.println("Trabajador agregado correctamente");
        }



    public void agregarProductos(Productos productos){
        listaProductos.add(productos);
        productosDTO.insertarProductos(productos);
        System.out.println("Productos insertados correctamente");

    }

    public boolean loginUsuario(String correo, String password) {
        boolean loginCorrecto = trabajadoresDTO.loginUsuario(correo, password);

        if (loginCorrecto) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Usuario no encontrado");
        }

        return loginCorrecto;
    }




    public void mostrarProductos() {
        for (Productos productos: listaProductos) {
            productosDTO.mostrarProductos(productos.getNombre() , productos.getPrecio() , productos.getCantidad());
        }
    }

    public void actualizarProductos(){
        for (Productos productos: listaProductos) {
            productosDTO.actualizarPrecioNombre(productos);
        }
    }


}
