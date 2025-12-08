package EjercicioRecordatorio

import java.util.concurrent.Executors

fun main() {
    val ejecutar = Executors.newFixedThreadPool(3)

    val tareas= listOf(
        Tarea("Tarea 1"),
        Tarea("Tarea 2"),
        Tarea("Tarea 3"),
        Tarea("Tarea 4"),
        Tarea("Tarea 5"),
        Tarea("Tarea 6")
    )

    for (tarea in tareas){
        ejecutar.execute(tarea)
    }
    ejecutar.shutdown()
}

class Tarea(private val nombre:String): Runnable{
    override fun run() {
        println("Inicio tarea $nombre")
        for (i in 1..10){
            Thread.sleep(1000)
        }
    }
}