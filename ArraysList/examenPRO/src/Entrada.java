import model.*;
import controller.Tienda;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Scanner scanner = new Scanner(System.in);


        tienda.agregarVehiculo(new Coche("nuevo", "Toyota", "Corolla", "1111", 20000, 150, 2000));
        Moto moto = new Moto("seminuevo", "Honda", "CBR", "2222", 8000, "Manual", 180);
        Patinete patinete = new Patinete("seminuevo", "Xiaomi", "Mi Electric", "5555", 250, 25);
        Bici bici = new Bici("nuevo", "Giant", "Bmx", "3333", 600, "MTB");

        




        int opcion;
        do {
            System.out.println("Introduce una opcion a realizar");
            opcion= scanner.nextInt();
            System.out.println("1. Agregar vehiculo ");
            System.out.println("2. Buscar en stock ");
            System.out.println("3. Vender vehiculo ");
            System.out.println("4. Reparar y agregar vehiculo ");
            System.out.println("5. Ver resultado de caja ");
            System.out.println("6. Salir del programa ");

            switch (opcion){
                case 1:
                    tienda.agregarVehiculo(scanner, coche, moto,bici,patinete);
                    break;
                case 2:
                    tienda.buscarPorNumeroSerie(scanner, patinete.getnumeroSerie() );
                    break;
                case 3:
                    tienda.venderVehiculo(scanner, coche.getnumeroSerie());
                    break;
                case 4:
                    tienda.repararVehiculo(scanner, coche.getnumeroSerie());
                    break;
                case 5:
                    tienda.verCaja();
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicacion.... ");
                    break;
                default:
                    System.out.println("Accion invalida, intentalo de nuevo ");
                    break;
            }

        }while(opcion!=7);


    }

    Scanner scanner = new Scanner(System.in);
    private  void crearEmpresa(){
        System.out.println("Introduce el nombre de una empresa");
        String nombre= scanner.next();
        System.out.println("");
    }
    private void registrarVehiculo(){}
    private void repararVehiculo(Bici bici, Patinete patinete){}
    private void repararVehiculo(Moto moto){}
    private void mostrarDatos(){
    }
    private void venderVehiculos(){}
    private void mostrarCaja(){}
    }

