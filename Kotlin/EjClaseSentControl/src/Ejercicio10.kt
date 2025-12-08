fun main() {
    val numero= readLine()!!.toInt()
    var octal=numero.toString(8)
    var n= numero
    while (n<0){
        octal=(n % 8).toString()+octal
    }
    if (octal.isEmpty()){
        octal="01234567"
    }
    println("El numero $numero se le $octal en Octales")
}