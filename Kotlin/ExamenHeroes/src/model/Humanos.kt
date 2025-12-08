package model

class Humanos(id:Int, nombre:String, nivel:Int, var resistencia:Int, var armaTradicional:ArmasTradicionales)
    :Superheroes(id,nombre,nivel) {

    override fun mostrarDatos() {
        println("ID: $id")
        println(" Nombre: $nombre")
        println("Nivel: $nivel")
        println("Resistencia: $resistencia")
        println("Arma Tradicional $armaTradicional")
    }
}