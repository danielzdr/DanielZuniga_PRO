package model

abstract class Trabajador(nombre: String, apellido: String, dni: String, var salario: Double) :
    Persona(nombre, apellido, dni) {

    constructor(nombre: String, apellido: String, dni: String, salario: Double, telefono: Int, correoE: String, seguro: Boolean) :
            this(nombre, apellido, dni, salario) {
        this.telefono = telefono
        this.correoE = correoE
    }

    abstract fun calcularSalario(): Double

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Salario: $salario")
    }
}
