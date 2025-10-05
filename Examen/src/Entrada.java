import controller.PlataformaJuegos;
import model.*;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaJuegos plataformaJuegos = new PlataformaJuegos();
        int opcion = 0;
        Videojuego videojuego1 = new VideojuegoAccion("God of war" , "Pablo" , "Pegi 18" , 2005 , 70.0 , 5 , false , 50.0);
        Videojuego videojuego2 = new VideojuegoAccion("Call of duty" , "Ruben" , "Pegi 16" , 2015 , 75.0 , 4 , true , 40.0);
        Videojuego videojuego3 = new VideojuegoEstrategia("Ajedrez" , "Daniel" , "Pegi 3" , 2000 , 10.0 , 4 , 60 , 30.0);
        Videojuego videojuego4 = new VideojuegoEstrategia("Hundir la flota" , "Olek" , "Pegi 3" , 2001 , 9.99 , 5 , 120 , 10.0);
        Videojuego videojuego5 = new VideoJuegoRPG("GTA 5" , "Daniel" , "Pegi 18" , 2000 , 10.0 , true , 60 , 30.0);
        Videojuego videojuego6 = new VideoJuegoRPG("Assasin Creed" , "Arthur" , "Pegi 18" , 2019 , 60.50 , true , 120 , 25.75);
        do {
            System.out.println("Bienvenido a la plataforma de juegos");
            System.out.println("Introduzca una opcion: ");
            opcion = scanner.nextInt();
            System.out.println("1. Ver todos los juegos disponibles");
            System.out.println("2. Filtrar juegos por tipo(Acción, Estrategia, RPG)");
            System.out.println("3. Filtrar juegos por clasificacion de edad");
            System.out.println("4. Ver detalles de un juego especifico (seleccionado por indice)");
            System.out.println("5. Calcular tiempo de descarga de un juego especifico");
            System.out.println("6. Calcular precio total de todos los juegos");
            System.out.println("7. Salir del programa");
            switch (opcion) {
                case 1:
                    plataformaJuegos.añadirJuego(videojuego1);
                    break;
                case 2:
                    System.out.println("Juegos: Accion/Estrategia/RPG");
                    break;
                case 3:
                    System.out.print("Introduce clasificación de edad: ");
                    String clas = scanner.nextLine();
                    plataformaJuegos.filtrarPorClasificacion(clas);
                    break;
                case 4:
                    System.out.print("Introduce índice del juego: ");
                    int idx = scanner.nextInt();
                    if (idx >= 0 && idx < plataformaJuegos.getJuegos().size())
                        System.out.println(plataformaJuegos.getJuegos().get(idx));
                    else System.out.println("Índice inválido.");
                    break;
                case 5:
                    System.out.print("Introduce índice del juego: ");
                    idx = scanner.nextInt();
                    System.out.print("Velocidad de internet (Mbps): ");
                    double vel = scanner.nextDouble();
                    if (idx >= 0 && idx < plataformaJuegos.getJuegos().size()) {
                        Videojuego juego = plataformaJuegos.getJuegos().get(idx);
                        if (juego instanceof Descargable d) {
                            System.out.printf("Tiempo estimado de descarga: %.2f minutos\n" , d.calcularTiempoDescarga(vel));
                        }
                    }

                    break;
                case 6:

                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida, intentalo de nuevo");
                    break;
            }

        } while (opcion != 9);
    }
}

