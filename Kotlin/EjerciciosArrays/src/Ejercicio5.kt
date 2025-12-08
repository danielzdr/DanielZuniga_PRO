fun main() {
    val numeros=(1..5).toList()
    val multiplicados= numeros.map { it*2 }
    println("Los numeros multiplicados por dos: ")
    multiplicados.forEach { println(it) }
}