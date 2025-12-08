fun main() {
    var numeros= 20
    val fibonacci= IntArray(numeros)
    fibonacci[0]=0
    fibonacci[1]=1
    for (i in 2 until numeros){
        fibonacci[i]= fibonacci[i-1]+fibonacci[i-2]
    }

    for (num in fibonacci){
        println(num)
    }

}