import controller.PlataformaJuegos;
import model.*;
import java.util.Scanner;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaJuegos plataformaJuegos = new PlataformaJuegos();


        plataformaJuegos.añadirJuego(new VideojuegoAccion("God of War", "Pablo", "Pegi 18", 2005, 70.0, 5, false, 50.0));
        plataformaJuegos.añadirJuego(new VideojuegoAccion("Call of Duty", "Ruben", "Pegi 16", 2015, 75.0, 4, true, 40.0));
        plataformaJuegos.añadirJuego(new VideojuegoEstrategia("Ajedrez", "Daniel", "Pegi 3", 2000, 10.0, 4, 60, 30.0));
        plataformaJuegos.añadirJuego(new VideojuegoEstrategia("Hundir la Flota", "Olek", "Pegi 3", 2001, 9.99, 5, 120, 10.0));
        plataformaJuegos.añadirJuego(new VideoJuegoRPG("GTA 5", "Daniel", "Pegi 18", 2000, 10.0, true, 60, 30.0, true));
        plataformaJuegos.añadirJuego(new VideoJuegoRPG("Assassin Creed", "Arthur", "Pegi 18", 2019, 60.50, true, 120, 25.75, false));

        int opcion = 0;

        do {
            System.out.println("Bienvenido a la plataforma de juegos");
            System.out.println("1. Ver todos los juegos disponibles");
            System.out.println("2. Filtrar juegos por tipo (Acción, Estrategia, RPG)");
            System.out.println("3. Filtrar juegos por clasificación de edad");
            System.out.println("4. Ver detalles de un juego específico (por índice)");
            System.out.println("5. Calcular tiempo de descarga de un juego específico");
            System.out.println("6. Calcular precio total de todos los juegos");
            System.out.println("7. Salir del programa");
            System.out.print("Introduce una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    plataformaJuegos.listarJuegos();
                    break;
                case 2:
                    System.out.print("Introduce tipo de juego (Accion/Estrategia/RPG): ");
                    String tipo = scanner.nextLine();
                    plataformaJuegos.filtrarPorTipo(tipo);
                    break;
                case 3:
                    System.out.print("Introduce clasificación de edad: ");
                    String clas = scanner.nextLine();
                    plataformaJuegos.filtrarPorClasificacion(clas);
                    break;
                case 4:
                    System.out.print("Introduce índice del juego: ");
                    int idx = scanner.nextInt();
                    if (idx >= 0 && idx < plataformaJuegos.getJuegos().size()) {
                        System.out.println(plataformaJuegos.getJuegos().get(idx));
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 5:
                    System.out.print("Introduce índice del juego: ");
                    idx = scanner.nextInt();
                    System.out.print("Velocidad de internet (Mbps): ");
                    double vel = scanner.nextDouble();
                    if (idx >= 0 && idx < plataformaJuegos.getJuegos().size()) {
                        Videojuego juego = plataformaJuegos.getJuegos().get(idx);
                        if (juego instanceof Descargable d) {
                            double tiempoSegundos = d.calcularTiempoDescarga(vel);
                            System.out.printf("Tiempo estimado de descarga: %.2f minutos\n", tiempoSegundos / 60);
                        } else {
                            System.out.println("Este juego no es descargable.");
                        }
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 6:
                    double total = plataformaJuegos.calcularPrecioTotal();
                    System.out.printf("Precio total de todos los juegos: %.2f€\n", total);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, inténtalo de nuevo");
                    break;
            }
        } while (opcion != 7);

        scanner.close();
    }
}



