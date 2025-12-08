package Ejercicio2

import java.util.concurrent.TimeUnit
import kotlin.time.measureTime

fun Descargar(){
    println("Comienzo a descargar..")
    TimeUnit.SECONDS.sleep(3)
    println("Fin, lo he descargado")
}

fun main() {
    val tiempo= measureTime {
        for (i in 1..3){
            Descargar()
        }
    }
    println(tiempo)
}