import controller.ProductController;
import model.ProductDTO;

public class Entrada {
    public static void main(String[] args) {

        ProductController controller = new ProductController();
        
        controller.cargarProductosDesdeJSON();
    }
}
