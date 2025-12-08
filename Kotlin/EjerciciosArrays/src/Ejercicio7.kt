fun main() {
val nombres= arrayOf("Señor de los anillos","star wars","harry potter", "naruto", "one piece")
    val cadena= nombres.joinToString (separator = ",")
    println("La cadena $cadena ")
}