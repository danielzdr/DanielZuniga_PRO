package EjerciciosStrControl

fun main() {
    println("Introduce un numero: ")
    val numero = readLine()!!.toInt()

    if (numero %2==0){
        println("El numero es $numero par")
    }else{
        println("El numero es $numero impar")
    }
}