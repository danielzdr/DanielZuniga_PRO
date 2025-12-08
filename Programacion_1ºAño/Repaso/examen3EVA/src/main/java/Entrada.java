import DTO.ProductosDTO;
import DTO.TrabajadoresDTO;
import controller.Tienda;
import model.Productos;
import model.Trabajadores;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Trabajadores trabajadores= new Trabajadores();
        TrabajadoresDTO trabajadoresDTO= new TrabajadoresDTO();
        ProductosDTO productosDTO= new ProductosDTO();
        Tienda tienda = new Tienda(trabajadoresDTO);
        Tienda tienda2= new Tienda(productosDTO);




        //tienda.loginUsuario(correo,password);
        for (int i = 0; i < 1; i++) {

            System.out.println("Introduce el nombre de un trabajador");
            String nombreTrabajador = scanner.next();
            System.out.println("Introduce un salario para el trabajador");
            int salario = scanner.nextInt();
            System.out.println("Introduce un cargo para el trabajador");
            String cargo = scanner.next();
            System.out.println("Introduce un correo de login");
            String correo = scanner.next();
            System.out.println("Introduce una contraseña valida");
            String password = scanner.next();

            System.out.println("Trabajador"+(i+1));

            tienda.agregarTrabajador(new Trabajadores(salario , nombreTrabajador , cargo , correo , password));

        }
       /* System.out.println("Introduce un nombre para el producto");
        String nombreProducto= scanner.next();
        int precio= scanner.nextInt();
        System.out.println("Introduce una cantidad");
        int cantidad= scanner.nextInt();

        tienda.agregarProductos(new Productos(precio,cantidad,nombreProducto));

         /*
        System.out.println("Introduce un ID para borrar un producto");
        int id= Integer.parseInt(scanner.nextLine());
        productosDTO.borrarProducto(id);

        /*
         tienda.loginUsuario(correo,password);
        System.out.println("Introduce un precio");
        int precio= scanner.nextInt();
        System.out.println("Introduce una cantidad");
        int cantidad= scanner.nextInt();
        System.out.println("Introduce un nonbre para el producto");
        String nombreProducto= scanner.next();
        tienda2.mostrarProductos();
        tienda2.actualizarProductos();
        tienda.agregarProductos(new Productos(precio,cantidad,nombreProducto));
        tienda.agregarTrabajador(new Trabajadores(salario,nombreTrabajador,cargo,correo,password));
        productosDTO.actualizarPrecioNombre(precio, nombre);
        productosDTO.mostrarProductos(nombre,precio, cantidad);
        productosDTO.borrarProducto(id);
        productosDTO.mostrarProductos(nombre,precio, cantidad);*/
    }
}
