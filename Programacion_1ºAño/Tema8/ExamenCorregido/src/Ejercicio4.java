import model.Inventario;

public class Ejercicio4 {
    public static void main(String[] args) {
        Inventario<Inventario.Producto> inventario = new Inventario();
        inventario.agregarElemento(inventario.new Producto("Queso",34,5));
        inventario.agregarElemento(inventario.new Producto("Jamon",20,10));
        inventario.agregarElemento(inventario.new Producto("Pan",12,34));

        inventario.mostrarElemento();
    }
}
