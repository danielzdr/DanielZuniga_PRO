package model

class Administrador(id: Int,nombre:String, var clave:Int): Persona(id,nombre) {

    fun validarAcceso(claveIngresada: Int): Boolean {
        return clave == claveIngresada

    }
    override fun mostrarDatos() {
        println("ID: $id")
        println("Nombre: $nombre")
        println("Clave: $clave")
    }

}