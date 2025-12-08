fun main() {
println(numerosParImpar(3))
}

fun numerosParImpar(numero:Int):Boolean{
    if (numero %2==0){
       println("El numero es par")
        return true
    }else{
        println("El numero es impar")
    }
    return false
}