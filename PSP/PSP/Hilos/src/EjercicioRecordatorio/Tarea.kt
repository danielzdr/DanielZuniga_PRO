package EjercicioRecordatorio

import java.util.concurrent.Executors

fun main() {
    val ejecutar=Executors.newSingleThreadExecutor()
    ejecutar.execute{
        println("soy un hilo")
    }
    ejecutar.shutdown()
}