fun main() {
    var precio:Double= 100.0
    println("El pecio base es de $precio")
    fun recibirDescuento():Unit{
        val descuento = when {
            precio >= 50 -> 0.10
            else -> 0.05
        }
        precio -= precio * descuento
    }
    recibirDescuento()
    println("El precio final es de $precio")
}