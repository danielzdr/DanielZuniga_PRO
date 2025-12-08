package model

open class Superheroes(var id:Int, var nombre:String, var nivel:Int) {

open fun mostrarDatos(){
    println("ID: $id")
    println(" Nombre: $nombre")
    println("Nivel: $nivel")
}
}