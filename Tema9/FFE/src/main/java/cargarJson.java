import DTO.JsonReader;
import database.DB;
import org.json.JSONArray;
import org.json.JSONObject;

public class cargarJson {

    public static void main(String[] args) {
        String rutaJson = "C:\\Users\\Usuario\\Documents\\GitHub\\DanielZuniga_PRO\\Tema9\\FFE\\src\\main\\java\\recursos\\ordenes_generadas_completo.json";

        try {
            JSONArray productos = JsonReader.leerJsonDesdeArchivo(rutaJson);

            for (int i = 0; i < productos.length(); i++) {
                JSONObject producto = productos.getJSONObject(i);
                if ("probado en ot".equals(producto.optString("comentario"))) {
                    DB.guardarProducto(producto);
                }
            }

            System.out.println("Productos insertados correctamente.");
        } catch (Exception e) {
            System.err.println(" Error en el proceso: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

