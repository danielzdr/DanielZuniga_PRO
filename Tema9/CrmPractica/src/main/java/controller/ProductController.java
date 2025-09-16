package controller;

import model.Product;
import model.ProductDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class ProductController {

    public void cargarProductosDesdeJSON() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ordenes_generadas_completo.json");

            if (inputStream == null) {
                throw new RuntimeException("No se encontró el archivo JSON en resources.");
            }

            JSONTokener tokener = new JSONTokener(inputStream);
            JSONArray array = new JSONArray(tokener);

            ProductDTO productDTO = new ProductDTO();
            int insertados = 0;

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String comentario = obj.optString("comment", "").toLowerCase().trim();

                System.out.println("Comentario encontrado: [" + comentario + "]");

                if (comentario.contains("probando en ot")) {
                    try {
                        Product p = new Product();
                        p.setCode(obj.optString("code"));
                        p.setClient(obj.optString("client"));
                        p.setDescription(obj.optString("description"));
                        p.setUnits(obj.optInt("units"));
                        p.setTotal(obj.optDouble("total"));
                        p.setComment(comentario);

                        productDTO.insertarProducto(p);
                        insertados++;

                    } catch (Exception inner) {
                        inner.printStackTrace();

                    }
                }
            }

            System.out.println("Carga completada. Productos insertados: " + insertados);

        } catch (Exception e) {
            System.err.println("Error al cargar JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
