fun main() {
    val numeros=(1..5).toList()
    val comprobar= numeros.all { it<10 }
    print("¿Numeros menores a 10? $comprobar")
}