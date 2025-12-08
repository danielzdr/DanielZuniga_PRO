package model

class ArmasTradicionales(id:Int, nombre:String, nivelPotencia:Int,nivelDanio:Int, var peso:Int):
    Armas(id,nombre,nivelPotencia,nivelDanio) {

    override fun mostrarDatos() {
        println("ID: $id")
        println("Nombre: $nombre")
        println("Nivel de potencia: $nivelPotencia")
        println("Nivel de Daño $nivelDanio")
        println("Peso: $peso")
    }
}