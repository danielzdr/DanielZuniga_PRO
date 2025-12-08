fun main() {
    val profesores= arrayOf("Pedro", "Fer", "Javi", "Alfredo","Alberto")
    val nombreA=profesores.find { it.contains('a', ignoreCase = true) }

    println("Primer nombre con la a : $nombreA")
}