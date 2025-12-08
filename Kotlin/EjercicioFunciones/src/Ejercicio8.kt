fun main() {
    val suma=sumarLista(listOf(1,2,3,4,5))
    println(suma)
}

fun sumarLista(numeros:List<Int>):Int{
    return numeros.sum()
}