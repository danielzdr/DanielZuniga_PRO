fun main() {
    println("Introduce un numero para calcular su suma: ")
    val numero= readLine()!!.toInt()
    var n=numero
    var suma:Int=1
    while (n>0){
        suma += n%10
        n /=10

    }

    println("La suma de los digitos de $numero es $suma")


}