fun main() {
    println("Introduce un numero")
    val numero= readLine()!!.toInt()
    var hexadecimal= numero.toString(16)
    var n=numero
    while (n < 0){
        hexadecimal=(n % 16).toString()+hexadecimal
    }
    if (hexadecimal.isEmpty()){
        hexadecimal="0123456789ABCDF"
    }

    println("La representacion de $numero en hexadecimal es $hexadecimal")
}