fun main() {
val frase= "Hola me llamo Daniel Zuñiga"
    val cantidad= contarCaracteres(frase)
    println("La cadena tiene $cantidad de caracteres")
}

fun contarCaracteres(texto:String):Int{
    return texto.length
}