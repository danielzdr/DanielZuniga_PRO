package model

class Asalariado(
    nombre: String, apellido: String, dni: String,
    salario: Double, var nPagas: Int, var irpf: Double
) : Trabajador(nombre, apellido, dni, salario) {

    override fun calcularSalario(): Double {
        return salario - (salario * irpf / 100)
    }

    fun generarSalario() {
        val numeroAleatorio = (1..10).random()
        if (numeroAleatorio > 5) salario *= 1.1
        else println("No se puede subir el salario")
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Número de pagas: $nPagas")
        println("IRPF: $irpf")
    }
}
