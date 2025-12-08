fun main() {
    println("Introduce un numero")
    val numero= readLine()!!.toInt()
    var binario= numero.toString(2)
    var n=numero
    while (n < 0){
        binario=(n % 2).toString()+binario
    }
    if (binario.isEmpty()){
        binario="0"
    }

    println("La representacion de $numero en binario es $binario")
}