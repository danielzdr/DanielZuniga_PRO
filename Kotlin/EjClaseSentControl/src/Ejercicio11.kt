fun main() {
    println("Introduce la altura de un triangulo: ")
    var altura= readLine()!!.toInt()
    for (i in 1..altura){
        for (j in 1 .. i){
            println("*")
        }
        println()
    }


}