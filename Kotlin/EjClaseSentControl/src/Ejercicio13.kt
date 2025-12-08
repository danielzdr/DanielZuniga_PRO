fun main() {
    println("Introduce un numero: ")
    val numero = readLine()!!.toInt()

    val fibonacci= mutableListOf<Int>()
    for (i in 0 until numero){
        when(i){
            0-> fibonacci.add(0)
            1-> fibonacci.add(1)
            else-> fibonacci.add(fibonacci[i-1]+fibonacci[i-2])
        }

    }
    println("Serie de Finobbacci hasta el termino $numero: ${fibonacci.joinToString (", ")}")
}