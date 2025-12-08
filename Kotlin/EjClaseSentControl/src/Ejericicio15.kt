fun main() {
println("Introduce un numero: ")
    val numero = readLine()!!.toInt()
    var sumaDivisores=0

    for (i in 1 until numero){
        if (numero %i ==0){
            sumaDivisores+=i
        }
    }
    if (sumaDivisores==numero){
        println("El numero $numero es perfecto")
    }else{
        println("El numero $numero no es perfecto")
    }

}