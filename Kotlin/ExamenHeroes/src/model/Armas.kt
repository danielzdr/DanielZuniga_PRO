package model

open class Armas(var id:Int, var nombre:String, var nivelPotencia:Int, var nivelDanio:Int) {

    open fun mostrarDatos(){
        println("ID: $id")
        println("Nombre: $nombre")
        println("Nivel de potencia: $nivelPotencia")
        println("NIvel de Daño $nivelDanio")
    }
}