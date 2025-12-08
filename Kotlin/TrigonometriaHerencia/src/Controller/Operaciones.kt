package Controller

import model.Circulo
import model.Figura
import model.Rectangulo
import model.Triangulo

class Operaciones {
    val figuras= ArrayList<Figura>()

    fun crearCirculo(radio: Double,nombre:String,) {
        figuras.add(Circulo(radio,nombre))
        println("Círculo creado con radio $radio")
    }

    fun crearRectangulo(nombre: String,base: Double, altura: Double) {
        figuras.add(Rectangulo(nombre,base, altura))
        println("Rectángulo creado con base $base y altura $altura")
    }

    fun crearTriangulo(nombre:String,base: Double, altura: Double) {
        figuras.add(Triangulo(nombre,base, altura))
        println("Triángulo creado con base $base y altura $altura")
    }

    fun listarFiguras() {
        if (figuras.isEmpty()) {
            println("No hay figuras registradas")
            return
        }
        println("\n--- Figuras Registradas ---")
        for ((index, figura) in figuras.withIndex()) {
            println("${index + 1}. ${figura.nombre} - Área: ${"%.2f".format(figura.calcularArea())}")
        }
    }
}