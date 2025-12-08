package model

open class Persona(var nombre: String, var apellido: String, var dni: String) {
    var telefono: Int? = null
    var correoE: String? = null

    constructor(nombre: String, apellido: String, dni: String, telefono: Int?, correo: String?) : this(nombre, apellido, dni) {
        this.telefono = telefono
        this.correoE = correo
    }

    open fun mostrarDatos() {
        println("Nombre: $nombre")
        println("Apellido: $apellido")
        println("DNI: $dni")
        println("Teléfono: ${telefono ?: "no especificado"}")
        println("Correo: ${correoE ?: "no especificado"}")
    }
}

