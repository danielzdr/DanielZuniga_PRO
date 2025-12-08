fun main() {
    val minimo=numeroMenor(listOf(99,45,67,23,56))
    println(minimo)
}

fun numeroMenor(numeros: List<Int>): Int? {
    if (numeros.isEmpty()) return null

    var menor = numeros[0]
    for (num in numeros) {
        if (num < menor) menor = num
    }
    return menor
}