package EjerciciosStrControl

fun main() {
    println("Introduce un numero")
    val numero = readLine()!!.toInt()
    val primos = mutableListOf<Int>()

    for (i in 2..numero) {
        if (numeroPrimos(i)) {
            primos.add(i)
        }
    }

    println("Los números primos menores o iguales a $numero son: ${primos.joinToString(", ")}")
}

fun numeroPrimos(num:Int):Boolean{
    if (num<=1)return false
    if (num<=3)return true

    if (num %2==0 || num %3==0) return false

    var i= 5
    while (i*i <=num ){
        if (num % i == 0 || num % (i + 2) == 0){
            i+=6
        }
    }
    return true
}
