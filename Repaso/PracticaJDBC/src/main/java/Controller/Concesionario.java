package Controller;
import model.Coche;
import model.Pasajeros;
import java.io.*;
import java.util.ArrayList;

public class Concesionario {
    private ArrayList<Coche> listaCoches= new ArrayList<>();
    private static final String BINARY_FILE = "coches.dat";
    private static final String CSV_FILE = "coches.csv";
    private ArrayList<Pasajeros> listaPasajeros;

    public Concesionario() {
        this.listaCoches = new ArrayList<>();
        cargarDatosDesdeArchivo();
    }

    public void anadirCoche(Coche coche){
        listaCoches.add(coche);


    }

    public boolean borrarCoche(String matricula) {
        return listaCoches.removeIf(c -> c.getMatricula() == matricula);
    }

    public Coche consultarCoche(int id){
        for (Coche coche: listaCoches){
            if (coche.getId()==id){
                return coche;
            }
        }
        return null;
    }

    public void listarCoches(){
        if (listaCoches.isEmpty()){
            System.out.println("No hay coches registrados");
        }else {
            System.out.println("Listado de los coches guardados");
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }
        }
    }


    public void anadirPasajero(Pasajeros pasajeros){
        listaPasajeros.add(pasajeros);
    }

    public boolean borraPasajeros(int id){
        return listaPasajeros.removeIf(c-> c.getId() == id);
    }

    public Pasajeros consultarPasajero(int id){
        for (Pasajeros pasajeros: listaPasajeros){
            if (pasajeros.getId()== id){
                return pasajeros;
            }
        }
        return null;
    }

    public void listarTodosLosPasjeros(){
        if (listaPasajeros.isEmpty()){
            System.out.println("No hay pasajeros registrados");
        }else {
            System.out.println("Listado de los coches guardados");
            for (Pasajeros pasajeros: listaPasajeros){
                System.out.println(pasajeros);
            }
        }
    }

    // Metodo para cargar datos al iniciar
    private void cargarDatosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BINARY_FILE))) {
            listaCoches = (ArrayList<Coche>) ois.readObject();
            System.out.println("Datos de coches cargados desde " + BINARY_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo " + BINARY_FILE + ". Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo " + BINARY_FILE);
        }
    }

    // Metodo para guardar datos al salir
    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BINARY_FILE))) {
            oos.writeObject(listaCoches);
            System.out.println("Datos guardados en " + BINARY_FILE);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos");
        }
    }

    // Metodo para exportar a csv
    public void exportarCSV() {
        File file = new File("src/main/java/Resources/coches.csv");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(String.valueOf(file) , true))) {
            // Escribir cabecera
            printWriter.println("ID;Matricula;Marca;Modelo;Color");

            // Escribir datos
            for (Coche coche : listaCoches) {
                printWriter.println(coche.toCSV());
            }

            System.out.println("Datos exportados correctamente a " + CSV_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Error al crear el archivo CSV");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
