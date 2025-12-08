package model

class ArmasBiologica(id:Int, nombre:String, nivelPotencia:Int,nivelDanio:Int, var descripcion:String):Armas(id,nombre,nivelPotencia,nivelDanio) {

    override fun mostrarDatos(){
        println("ID: $id")
        println("Nombre: $nombre")
        println("Nivel de potencia: $nivelPotencia")
        println("NIvel de Daño $nivelDanio")
        println("Descipcion: $descripcion")
    }
}