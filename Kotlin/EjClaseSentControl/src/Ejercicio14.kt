fun main() {
    println("Introduce un numero: ")
    val numero1= readLine()!!.toInt()
    println("Introduce el segundo numero")
    val numero2= readLine()!!.toInt()

    val mcd= calcularMCD(numero1,numero2)
    println("El MCD  de $numero1 y $numero2 es $mcd ")



}

fun calcularMCD(numero1: Int, numero2: Int): Any {
    var num1 = numero1
    var num2 = numero2
    var item: Int

    while (num2 != 0) {
        item = num2
        num2 = num1 % num2
        num1 = item
    }

    return num1
}


