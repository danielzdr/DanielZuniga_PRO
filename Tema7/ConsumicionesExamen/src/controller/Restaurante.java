package controller;

import model.*;
import util.DatosFiscales;
import util.Proveedor;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    private ArrayList<Cliente> listaClientes;
    private double caja;
    private Proveedor proveedor;

    public Restaurante(Proveedor proveedor) {
        listaClientes=new ArrayList<>();
        this.proveedor=proveedor;
        //proveedor= null
    }

    public void agregarCliente(Cliente cliente){
        // no pido los datos aqui!!!, esto lo hace la ENTRADA - UI->l controlador es la LOGICA
        // metera al cliente dentro de la lista
        if (estaCliente(cliente.getNombre())!=null){
            System.out.println("Ya esta en el resutaurante");
        } else {
            listaClientes.add(cliente);
            System.out.println("Bienvenido al restaurante");
        }
    }

    public void mostrarClientes(){
        for (Cliente cliente:listaClientes){
            cliente.mostrarDatos();
        }
    }

    private Cliente estaCliente(String nombre){
        for (Cliente cliente : listaClientes){
            if (cliente.getNombre().equalsIgnoreCase(nombre)){
                return cliente;
            }
        }
        return null;
    }



    public void agregarPedido(Consumicion consumicion, String nombre){
        Cliente cliente=estaCliente(nombre);
        if (!estaConsumicion(consumicion.getNombre(),cliente )){
            cliente.getConsumiciones().add(consumicion);
        }else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veo que ya tienes una consumiciones, quieres pedir otro");
            String respuesta= scanner.next();
            if (respuesta.equalsIgnoreCase("s")){
                System.out.println("Cuantas quieres de mas");
                int numero= scanner.nextInt();
                for (int i = 0; i < 1; i++) {

                }
            }
        }
    }

    private boolean estaConsumicion(String nombre, Cliente cliente){
        for (Consumicion consumicion: cliente.getConsumiciones()){
            if (consumicion.getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }

    public void listaConsumiciones(){
        for (Cliente cliente: listaClientes){
            System.out.println(cliente);
        }
    }

    public void totalPagar(){}

    public void verCaja(){}

    public void informacionProveedor(){
        if (proveedor==null){
            System.out.println("El restaurante aun no tiene un proveedor asociado");
        }else {
            System.out.println("La informacion del proveedor es ");
            System.out.println(proveedor.getNombre());
            System.out.println(proveedor.getDescuento());
            System.out.println(proveedor.getContacto());
        }
    }

    public void  mostrarDatosFiscales(){
        System.out.println("El NIF es "+ DatosFiscales.NIF);
        System.out.println("La direccion es "+ DatosFiscales.DIR_FISCAL);
        System.out.println("El IVA es "+ DatosFiscales.IVA);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
