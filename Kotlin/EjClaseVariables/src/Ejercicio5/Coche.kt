package Ejercicio5

open class Coche(tipo:String, marca:String,modelo:String, velocidad:Int, anio:Int,
            var deportivo:String)
    :Vehiculo(tipo,marca,modelo, velocidad,anio) {

fun mostrarCoche(){
    println("Coche $marca $modelo")
    println("Velocidad actual $velocidad")
    println("Año $anio")
    println("Deportivo $deportivo")
}

fun acelerarCoche(){
    acelerar(20)
}

}