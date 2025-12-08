fun main() {

    val numeros= listOf(1,2,3,4,5,6,7,8,9,10)
    val maximo = encontrarMaximo(numeros)
    println("El número máximo es: $maximo")


}

fun encontrarMaximo(numeros: List<Int>): Int? {
    if (numeros.isEmpty()) return null

    var maximo = numeros[0]
    for (num in numeros) {
        if (num > maximo) {
            maximo = num
        }
    }
    return maximo
}