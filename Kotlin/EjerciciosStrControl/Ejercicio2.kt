package EjerciciosStrControl

fun main() {
    println("Introduce un numero")
    val numero= readLine()!!.toInt()
    if (numero > 0){
        println("El numero es $numero positivo")
    }else if (numero==0){
        println("El numero es $numero igual a 0")
    }else if (numero < 0){
        println("El numero es $numero negativo")
    }
}