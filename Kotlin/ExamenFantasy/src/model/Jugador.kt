package model
import kotlin.random.Random
class Jugador(id:Int, nombre:String, var posicion:String, var valor:Int): Persona(id,nombre) {

    fun calcularPuntos(): Int {
        return when (posicion) {
            "portero" -> (valor * 0.8).toInt() + Random.nextInt(4,20)
            "defensa" -> (valor * 0.7).toInt() + Random.nextInt(3, 15)
            "medio" -> (valor * 0.9).toInt() + Random.nextInt(5, 18)
            "delantero" -> (valor * 1.2).toInt() + Random.nextInt(2, 25)
            else -> 0
        }
    }

    override fun mostrarDatos() {
        println("Id: $id")
        println("Nombre $nombre")
        println("Posicion: $posicion")
        println("Valor: $valor")
    }
}