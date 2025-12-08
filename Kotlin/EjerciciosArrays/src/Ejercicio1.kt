fun main() {
    val nombres = Array(5) { "" }


    for (i in nombres.indices) {
        print("Introduce el nombre del compañero ${i + 1}: ")
        val nombre = readLine()!!.trim()
        nombres[i] = nombre
    }


    println("\nLos compañeros son:")
    for (nombre in nombres) {
        println(nombre)
    }
}