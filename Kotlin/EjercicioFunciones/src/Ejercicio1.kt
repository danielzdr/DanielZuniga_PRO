fun main() {
    val num1= readln().toInt()
    val num2= readln().toInt()
    fun sumarEnteros():Int{
        val suma= num1+num2
        return suma
    }
    println("La suma de los numeros enteros es ${sumarEnteros()}")
}