fun main() {
    val lista=generarLista(6)
    println(lista)
}

fun generarLista(n:Int):List<Int>{
    return (1..n).toList()
}