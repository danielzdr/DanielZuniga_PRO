import Controllador.Gestion
import model.*

fun main() {
    val gestion = Gestion()


        var opcion = 0

        do {
            println("1. Añadir trabajador")
            println("2. Eliminar trabajador")
            println("3. Listar trabajadores")
            println("4. Calcular salarios")
            println("5. Bajar sueldos")
            println("6. Salir")
            println("Introduce la opcion ")
            opcion = readLine()!!.toInt()

            when (opcion) {
                1 -> gestion.añadirTrabajador()
                2 -> gestion.eliminarTrabajador()
                3 -> gestion.listarTrabajadores()
                4 -> gestion.calcularSalarios()
                5 -> gestion.bajarSueldos()
                6 -> {
                    println("Saliendo del programa...")

                }

                else -> println("Opción no válida.")
            }
        }while (opcion!=6)

    }

