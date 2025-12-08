package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FacturasCSVReader {

    public static List<FacturaDTO> leerFacturasDesdeCSV(String path) {
        List<FacturaDTO> facturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Saltar cabecera
                    continue;
                }

                // Divide correctamente respetando las comillas
                List<String> datosList = new ArrayList<>();
                boolean dentroComillas = false;
                StringBuilder campo = new StringBuilder();

                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);

                    if (c == '"') {
                        dentroComillas = !dentroComillas;
                    } else if (c == ',' && !dentroComillas) {
                        datosList.add(campo.toString().trim());
                        campo.setLength(0);
                    } else {
                        campo.append(c);
                    }
                }
                datosList.add(campo.toString().trim());

                if (datosList.size() < 7) {
                    System.err.println("Línea con formato inválido: " + linea);
                    continue;
                }

                try {
                    FacturaDTO facturaDTO = new FacturaDTO();
                    facturaDTO.setNumeroFactura(datosList.get(0));
                    facturaDTO.setNombre(datosList.get(1));
                    facturaDTO.setApellido(datosList.get(2));
                    facturaDTO.setDireccion(datosList.get(3));
                    facturaDTO.setCorreo(datosList.get(4));
                    facturaDTO.setProductos(datosList.get(5));

                    String precioStr = datosList.get(6).replace("\"", "");
                    double precio = Double.parseDouble(precioStr);
                    facturaDTO.setPrecioFinal(precio);

                    facturas.add(facturaDTO);
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir el precio: " + datosList.get(6));
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facturas;
    }
}
