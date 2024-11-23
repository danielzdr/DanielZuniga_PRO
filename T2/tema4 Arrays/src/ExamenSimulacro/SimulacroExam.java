package ExamenSimulacro;

import java.lang.reflect.Array;
import java.util.Scanner;

public class SimulacroExam {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        String[] nombresIngredientes = {"Tomate" , "Baicon" , "Queso" , "Pollo " , "Cebolla", "Barbacoa", "Carne"};
        double[] preciosIngredientes = {0.50 , 0.30 , 1.00 , 1.50 , 0.40, 2.00, 2.45};
        double caja=0.0;
        int maxPedidos= 100;
        int [] id=new int[maxPedidos];
        int totalPedidos=0;
        String[] nombres = new String[maxPedidos];
        String[] telefonos = new String[maxPedidos];
        String[][] ingredientesPedidos = new String[maxPedidos][];
        double[] precios = new double[maxPedidos];
        String[] estados = new String[maxPedidos];
        int idContador=0;


        do {
            System.out.println("1. Realizar pedido");
            System.out.println("2. Servir pedido");
            System.out.println("3. Mostrar pedidos pendientes");
            System.out.println("4. Mostrar caja");
            System.out.println("5. Salir");
            System.out.println("Que opcion quieres elegir ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Nombre");
                    String nombre = scanner.next();
                    System.out.println("Telefono");
                    int telefono = scanner.nextInt();
                    System.out.println("Introduce la cantidad de ingrdientes que quieres añadir");
                    int cantidad = scanner.nextInt();
                    if (cantidad>1) {
                        System.out.println("\n Lista de ingredientes disponibles: ");
                        for (int i = 0; i < nombresIngredientes.length; i++) {
                            System.out.println((i+1) +" "+nombresIngredientes[i]+" "+preciosIngredientes[i]+"$");
                        }
                        String [] ingredientesSeleccionados=new String[cantidad];
                        double [] preciosSeleccionados=new double[cantidad];
                        System.out.println("\nSelecciona"+cantidad+"los ingredientes seleccionando sus numeros");
                        for (int i = 0; i < cantidad; i++) {
                            int seleccion;
                            System.out.println("Ingrediente"+(i+1)+": ");
                            seleccion= scanner.nextInt();
                            if (seleccion<1 && seleccion > nombresIngredientes.length) {
                                System.out.println("Accion invalida");
                            }
                            ingredientesSeleccionados[i]=nombresIngredientes[seleccion-1];
                            preciosSeleccionados[i]=preciosIngredientes[seleccion-1];
                        }
                        System.out.println("\nIngredientes seleccionados: ");
                        for (int i = 0; i < cantidad; i++) {
                            System.out.println(ingredientesSeleccionados[i]+" "+preciosSeleccionados[i]);
                        }
                    }else{
                        System.out.println("Cantidad invalida...");
                    }

                        break;
                        case 2:
                            id = new int[]{1 , 2 , 3};
                            estados = new String[]{"Pendiente" , "En proceso" , "Hecho"};
                            double [] Monto={15.50,20.00,11.75};
                            System.out.println("Introduce el id del pedido");
                            int idBuscado= scanner.nextInt();
                            boolean pedidoEncontrado=false;
                            for (int i = 0; i < id.length; i++) {
                                if (id[i] == idBuscado) {
                                    pedidoEncontrado = true;
                                    if (estados[i].equalsIgnoreCase("Servido")){
                                    System.out.println("El pedido con ID " + idBuscado + " ya ha sido servido");
                                } else {
                                    estados[i] = "Servido";
                                    System.out.println("El pedido con ID " + idBuscado + " ya ha sido servido");
                                    System.out.println("Cobro realizado con exito "+Monto[i]);
                                }
                            }
                            }
                            if (pedidoEncontrado!=pedidoEncontrado){
                                System.out.println("El pedido con ID "+idBuscado+" no existe");
                            }
                            break;
                        case 3:
                            System.out.println("\nPedidos pendientes");
                            for (int i = 0; i < totalPedidos; i++) {
                                if (estados[i].equals("Pendiente")){
                                    System.out.println("El ID "+id[i]+" ,Nombre: "+nombres[i]+" ,Telefono: "+telefonos[i]);
                                    System.out.println("Ingredientes: ");
                                    for(String ingrediente:ingredientesPedidos[i]){
                                        System.out.println(ingrediente+ " ");
                                    }
                                    System.out.println("\n");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Total recaudado del dia de hoy es "+caja+precios);
                            caja+=preciosIngredientes[idContador];
                            break;
                        case 5:
                            System.out.println("Saliendo del programa de la pizzeria.....");
                            break;
                        default:
                            System.out.println("Opcion no valida, vuelva a introducir una opcion");
                            break;
                    }

            } while (opcion != 6) ;

    }
}
