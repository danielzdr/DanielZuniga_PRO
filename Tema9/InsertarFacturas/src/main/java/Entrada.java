import database.DBConector;
import model.FacturaDTO;
import model.FacturasCSVReader;

import java.sql.Connection;
import java.util.List;

public class Entrada {
    private static FacturasCSVReader FacturaCSVReader;

    public static void main(String[] args) {
        String rutaCSV = "C:\\Users\\Usuario\\Documents\\GitHub\\DanielZuniga_PRO\\Tema9\\InsertarFacturas\\src\\main\\java\\facturas.csv";

        // Leer facturas desde el CSV

        List<FacturaDTO> facturas = FacturaCSVReader.leerFacturasDesdeCSV(rutaCSV);

        // Obtener conexión a la base de datos
        Connection conn = DBConector.getConnection();

        if (conn == null) {
            System.err.println("No se pudo conectar a la base de datos.");
            return;
        }

        // Insertar cada factura
        for (FacturaDTO dto : facturas) {
            try {
                dto.insert(conn);
                System.out.println("Factura insertada: " + dto.getNumeroFactura());
            } catch (Exception e) {
                System.err.println("Error al insertar factura: " + dto.getNumeroFactura());
                e.printStackTrace();
            }
        }

        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Proceso finalizado.");
    }
}



