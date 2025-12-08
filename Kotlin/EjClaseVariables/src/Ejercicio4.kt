class Temperatura(initialCelsius: Double) {

    var celsius: Double = initialCelsius
        get() = field
        set(value) {
            field = when {
                value < -273.15 -> {
                    println("Advertencia: La temperatura no puede ser menor que -273.15°C. Se ajusta al valor mínimo.")
                    -273.15
                }
                value > 100.0 -> {
                    println("Advertencia: La temperatura no puede ser mayor que 100°C. Se ajusta al valor máximo.")
                    100.0
                }
                else -> value
            }
        }
}


fun main() {
    val temp = Temperatura(25.0)
    println("Temperatura inicial: ${temp.celsius}°C")

    temp.celsius = -300.0  // Intento de asignar menor que cero absoluto
    println("Temperatura ajustada: ${temp.celsius}°C")

    temp.celsius = 150.0   // Intento de asignar mayor que 100°C
    println("Temperatura ajustada: ${temp.celsius}°C")

    temp.celsius = 37.0    // Valor dentro del rango
    println("Temperatura final: ${temp.celsius}°C")
}