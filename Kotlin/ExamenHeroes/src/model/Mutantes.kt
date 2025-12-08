package model

class Mutantes(id:Int, nombre:String, nivel:Int,var armaBiologica:ArmasBiologica): Superheroes(id,nombre,nivel){

    override fun mostrarDatos() {
        println("ID: $id")
        println(" Nombre: $nombre")
        println("Nivel: $nivel")
        println("Armas biologicas $armaBiologica")
    }
}