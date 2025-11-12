package controller;
import model.VideoJuegoRPG;
import model.Videojuego;
import model.VideojuegoAccion;
import model.VideojuegoEstrategia;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlataformaJuegos {
        private ArrayList<Videojuego> juegos = new ArrayList<>();

        public void añadirJuego(Videojuego videojuego) {
            juegos.add(videojuego);
        }
        public void eliminarJuego(int index) {
            if (index >= 0 && index < juegos.size()) {
                juegos.remove(index);
            }
        }


    public void listarJuegos() {
        if (juegos.isEmpty()) {
            System.out.println("No hay juegos disponibles en la plataforma.");
        } else {
            System.out.println("Listado de juegos disponibles:");
            for (Videojuego juego : juegos) {
                System.out.println(juego);
            }
        }
    }


    public void filtrarPorTipo(String tipo) {
        boolean encontrado = false;
        System.out.println("Filtrando juegos por tipo: " + tipo);
        for (Videojuego juego : juegos) {
            switch (tipo.toLowerCase()) {
                case "accion":
                    if (juego instanceof VideojuegoAccion) {
                        System.out.println(juego);
                        encontrado = true;
                    }
                    break;
                case "estrategia":
                    if (juego instanceof VideojuegoEstrategia) {
                        System.out.println(juego);
                        encontrado = true;
                    }
                    break;
                case "rpg":
                    if (juego instanceof VideoJuegoRPG) {
                        System.out.println(juego);
                        encontrado = true;
                    }
                    break;
                default:
                    System.out.println("Tipo de juego invalido.");
                    return;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron juegos de este tipo.");
        }
    }

    public void mostrarOrdenadosPorPrecio() {
        if (juegos.isEmpty()) {
            System.out.println("La lista de juegos está vacía");
        } else {
            System.out.println("Vamos a mostrar los juegos ordenados por precio:");
            List<Videojuego> listaOrdenada = new ArrayList<>(juegos);
            listaOrdenada.sort(Comparator.comparingDouble(Videojuego::calcularPrecioFinal));
            for (Videojuego juego : listaOrdenada) {
                System.out.println(juego + " Precio final: " + juego.calcularPrecioFinal() + "€");
            }
        }
    }

    public void filtrarPorClasificacion(String clasificacion) {
        System.out.println("Juegos con clasificación: " + clasificacion);
        boolean encontrado = false;
        for (Videojuego videojuego : juegos) {
            if (videojuego.getClasificacionEdad().equalsIgnoreCase(clasificacion)) {
                System.out.println(videojuego);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay juegos con esa clasificación.");
        }
    }

    public double calcularPrecioTotal() {
        double total = 0;
        for (Videojuego videojuego: juegos) {
            total += videojuego.calcularPrecioFinal();
        }
        System.out.println("Precio total de todos los juegos: " + total + "€");
        return total;
    }

        public ArrayList<Videojuego> getJuegos() { return juegos; }
    }


