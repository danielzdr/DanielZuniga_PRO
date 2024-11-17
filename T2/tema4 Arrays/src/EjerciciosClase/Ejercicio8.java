package EjerciciosClase;

import java.util.Random;
import java.util.Scanner;

public class Ejercicio8 {
    //8. (AhorcadoArray) Crear un array de String donde se guarden 20 palabras.
    // El sistema deberá de seleccionar una de las palabras pertenecientes al array.
    // Una vez seleccionada se jugará al juego del ahorcado donde la palabra a acertar será la seleccionada.
    // Para ello las normas son las siguientes:
    //    - Nada mas arrancar se mostrarán por consola tantos _ como letras tenga la palabra a acertar
    //    - Se pedirá por pantalla la letra con la que se quiere probar
    //    - En el caso de acertar se mostrará la letra en la posición correspondiente
    //    - En el caso de falla se restará una vida
    //    - El programa continuará hasta que:
    //    - Acierte la palabra
    //    - Me quede sin vidas
    public static void main(String[] args) {


        // Array de palabras para el juego
        String[] palabras = {
                "programacion" , "computadora" , "java" , "teclado" , "pantalla" ,
                "raton" , "internet" , "juego" , "inteligencia" , "artificial" ,
                "algoritmo" , "codigo" , "desarrollo" , "aplicacion" , "sistema" ,
                "variable" , "constante" , "operador" , "condicional" , "ciclo"
        };

        // Seleccionar una palabra aleatoria del array
        Random random = new Random();
        String palabraAdivinar = palabras[random.nextInt(palabras.length)];

        // Configuración inicial
        int vidas = 6; // Número de intentos antes de perder
        char[] progreso = new char[palabraAdivinar.length()];
        for (int i = 0; i < progreso.length; i++) {
            progreso[i] = '_'; // Inicializar con "_"
        }

        Scanner scanner = new Scanner(System.in);
        boolean palabraAcertada = false;

        // Juego del ahorcado
        while (vidas > 0 && !palabraAcertada) {
            System.out.print("Palabra: ");
            System.out.println(progreso);
            System.out.println("Vidas restantes: " + vidas);
            System.out.print("Introduce una letra: ");
            char letra = scanner.next().toLowerCase().charAt(0);

            // Comprobar si la letra está en la palabra
            boolean acierto = false;
            for (int i = 0; i < palabraAdivinar.length(); i++) {
                if (palabraAdivinar.charAt(i) == letra) {
                    progreso[i] = letra;
                    acierto = true;
                }
            }

            // Si la letra no estaba en la palabra, restar una vida
            if (!acierto) {
                vidas--;
                System.out.println("Letra incorrecta.");
            } else {
                System.out.println("¡Acertaste una letra!");
            }

            // Comprobar si la palabra ha sido completamente acertada
            palabraAcertada = true;
            for (char c : progreso) {
                if (c == '_') {
                    palabraAcertada = false;
                    break;
                }
            }
        }

        // Mostrar el resultado del juego
        if (palabraAcertada) {
            System.out.println("¡Felicidades! Has acertado la palabra: " + palabraAdivinar);
        } else if (palabras==palabras) {
            System.out.println("Introduce la palabra completa "+palabras);
        } else {
            System.out.println("Te has quedado sin vidas. La palabra era: " + palabraAdivinar);
        }


        scanner.close();
    }
}

