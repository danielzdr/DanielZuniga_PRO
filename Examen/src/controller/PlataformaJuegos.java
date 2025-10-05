package controller;
import model.Videojuego;
import java.util.ArrayList;
import java.util.Comparator;

public class PlataformaJuegos {
        private ArrayList<Videojuego> juegos = new ArrayList<>();

        public void añadirJuego(Videojuego videojuego) {
            juegos.add(videojuego);
        }
        public void eliminarJuego(int index) { if (index >= 0 && index < juegos.size()) juegos.remove(index); }

        public void mostrarOrdenadosPorPrecio() {
            juegos.stream()
                    .sorted(Comparator.comparingDouble(Videojuego::calcularPrecioFinal))
                    .forEach(v -> System.out.println(v + " -> Precio final: " + v.calcularPrecioFinal() + "€"));
        }

        public void filtrarPorClasificacion(String clasificacion) {
            juegos.stream()
                    .filter(v -> v.getClasificacionEdad().equalsIgnoreCase(clasificacion))
                    .forEach(System.out::println);
        }

        public void mostrarTodos() {
            for (int i = 0; i < juegos.size(); i++) {
                System.out.println(i + ". " + juegos.get(i));
            }
        }

        public double calcularPrecioTotal() {
            return juegos.stream().mapToDouble(Videojuego::calcularPrecioFinal1).sum();
        }

        public ArrayList<Videojuego> getJuegos() { return juegos; }
    }


