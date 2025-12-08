import java.sql.Array

fun main() {
    val numeros= (1..10).toList()
    val impares=numeros.filter { it %2!=0 }
    println("Numeros impares")
    impares.forEach{ println(it) }
}