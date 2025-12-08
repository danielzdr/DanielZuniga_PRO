fun main() {
    val numero= readLine()!!.toInt()
    var factorial:Long=1
    for (i in 1..numero){
        factorial=factorial*1
        factorial*=i.toLong()

    }
    println("El factorial de $numero = $factorial")

}