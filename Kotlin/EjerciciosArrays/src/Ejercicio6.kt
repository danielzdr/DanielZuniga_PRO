fun main() {
    val numeros=(1..10).toList()
    val suma= numeros.reduce { acc, num -> acc+num }
    println("La suma del 1 al 10 son: $suma")
}