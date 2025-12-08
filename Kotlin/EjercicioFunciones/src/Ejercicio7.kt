fun main() {
    val cuadrados= cuadradosLista(listOf(1,2,3,4,5))
    println(cuadrados)
}

fun cuadradosLista(numeros:List<Int>):List<Int>{
    return numeros.map { it*it }
}