package garage_hash;
import garage_hash.controller.Garaje;


public class Entrada {

    public static void main(String[] args) {
        Garaje garaje=new Garaje();
        garaje.agregarCoche();
        //garaje.modificarCoche("98765C");
        //garaje.getCoche("98765C");
        //garaje.borrarCoche("");
        //garaje.recorrerCochesKey();
        garaje.recorrerCochesElement();

    }
}
