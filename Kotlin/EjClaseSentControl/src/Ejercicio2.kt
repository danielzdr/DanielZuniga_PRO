fun main() {
    var numero= readLine()!!.toInt()
    if (numero>0){
        println("El numero es positivo")
    }else if (numero==0){
        println("El numero es igual a 0")
    }else{
        println("El numero es negativo")
    }
}