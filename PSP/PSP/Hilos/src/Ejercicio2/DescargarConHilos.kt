package Ejercicio2

import java.util.concurrent.TimeUnit
import kotlin.time.measureTime

fun Descargar2(){

    println("Comienzo a descargar..")
    TimeUnit.SECONDS.sleep(3)
    println("Fin, lo he descargado")
}

fun main() {
    val tiempo= measureTime {
        val hilos= listOf(
            Thread{ Descargar2() }, Thread{ Descargar2() }, Thread{ Descargar2() }
        )
        hilos.forEach { it.start() }
        hilos.forEach { it.join() }

    }
    println(tiempo)
}