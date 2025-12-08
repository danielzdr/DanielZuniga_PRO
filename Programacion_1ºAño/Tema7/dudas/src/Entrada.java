import controller.Heladeria;
import model.Helado;
import utils.Cantidad;
import utils.Sabores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Por favor introduce cuantos helados quieres: ");
        int cantidad=0;
        try {
            cantidad = scanner.nextInt();
            //me han puesto un dato menor -> da error
            if (cantidad <= 0) {
                throw new Cantidad("Error en la cantidad introducida");
            }

            Helado helado= new Helado(5, Sabores.Mango);
        }catch (Cantidad e){
            System.out.println(e.getMessage());
            cantidad=0;
        }catch (InputMismatchException e){
            System.out.println("Datos incorrectos");
        //util para ejecuciones obligatorias -> cerrado de flujos
        }finally {

        }
        System.out.println("La cantidad seleccionada es " +cantidad);

       // Sabores sabor1 = Sabores.Fresa;



    }
}
