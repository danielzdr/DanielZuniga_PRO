fun main() {
    val palabras= listOf("Daniel", "Zuñiga", "del", "Rio")
    val resultado=unirCadenas(palabras)
    println(resultado)
}

fun unirCadenas(cadenas:List<String>):String{
    return cadenas.joinToString(" ")
}