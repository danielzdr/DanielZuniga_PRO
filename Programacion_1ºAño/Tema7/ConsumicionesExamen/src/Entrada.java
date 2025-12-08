import controller.Restaurante;
import model.Cliente;
import util.Proveedor;

public class Entrada {
    public static void main(String[] args) {

       Restaurante restaurante= new Restaurante(Proveedor.BEBIDASSL);
       restaurante.informacionProveedor();
    }
}
