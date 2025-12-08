fun main() {
    println("Introduce un numero: ")
    val numero= readLine()!!.toInt()
    val reverso= numero.reversed()

    if (numero == reverso){
        println("El numero $numero es palimdromo")
    }else{
        println("El numero $numero no es palimdromo")
    }

}

private fun <T> Comparable<T>.reversed(): Any? {
    TODO("Not yet implemented")
}


