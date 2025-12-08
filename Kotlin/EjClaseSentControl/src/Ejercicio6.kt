fun main() {
    var numero= readLine()!!.toInt()
    var suma=0
    for (i in 1..numero){
        suma+=i
    }
    println("La suma de los 4 primeros $numero es $suma")

}