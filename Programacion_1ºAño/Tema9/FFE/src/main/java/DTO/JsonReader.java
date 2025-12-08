package DTO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class JsonReader {

    public static JSONArray leerJsonDesdeArchivo(String ruta) throws Exception {
        File archivo = new File(ruta);
        InputStream is = new FileInputStream(archivo);
        JSONTokener tokener = new JSONTokener(is);
        JSONArray jsonArray = new JSONArray(tokener);
        is.close();
        return jsonArray;
    }
}

