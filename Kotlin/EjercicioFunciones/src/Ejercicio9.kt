fun main() {
    val resultado = cadenasA(listOf("Hola", "Kotlin", "Mundo", "Amarillo"))
    println(resultado)
}

fun cadenasA(cadenas: List<String>):List<String>{
    return cadenas.filter { it.contains('a',ignoreCase = true) }
}