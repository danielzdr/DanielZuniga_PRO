import Controller.Operaciones
import model.Figura

fun main() {
    val operaciones= Operaciones()

    var opcion: Int

    do {
        println("\n--- Menú ---")
        println("1. Crear Círculo")
        println("2. Crear Rectángulo")
        println("3. Crear Triángulo")
        println("4. Listar figuras")
        println("5. Salir")
        print("Elige una opción: ")

        opcion = readLine()!!.toInt()

        when (opcion) {
            1 -> {
                print("Introduce el radio del círculo: ")
                val radio = readLine()?.toDouble()
                println("Introduce un nombre para el circulo: ")
                val nombre= readln()
                if (radio != null) operaciones.crearCirculo(radio,nombre) else println("Valor no válido")
            }
            2 -> {
                println("Inroduce un nombre para el rectangulo: ")
                val nombre = readln()
                print("Introduce la base del rectángulo: ")
                val base = readLine()?.toDouble()
                print("Introduce la altura del rectángulo: ")
                val altura = readLine()?.toDouble()
                if (base != null && altura != null) operaciones.crearRectangulo(nombre,base, altura)
                else println("Valores no válidos")
            }
            3 -> {
                println("Introduce un nombre para el triangulo: ")
                val nombre= readln()
                print("Introduce la base del triángulo: ")
                val base = readLine()?.toDouble()
                print("Introduce la altura del triángulo: ")
                val altura = readLine()?.toDouble()
                if (base != null && altura != null) operaciones.crearTriangulo(nombre,base, altura)
                else println("Valores no válidos")
            }
            4 -> {operaciones.listarFiguras()}
            5 -> println("Saliendo...")
            else -> println("Opción no válida")
        }
    } while (opcion != 5)

}