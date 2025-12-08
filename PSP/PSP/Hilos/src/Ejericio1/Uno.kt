package Ejericio1

fun main() {

    /*val hilo = Thread {
        println("Hilo empezando")
        for (i in 1..5){
            println("Hilo ejecutando...")
            Thread.sleep(1000)
        }
        println("Hilo acabando")
    }
        println(hilo.state)
        hilo.priority= Thread.MAX_PRIORITY//->para darle mas rapidez al proceso
        hilo.start()// para mostrar por pantalla nuestra funcion lamda
        hilo.join()//para terminar el proceso-> TERMINATED
        println(hilo.state)*/

    val liebre = Thread {
        try {
            while (!Thread.currentThread().isInterrupted) {
                println("Liebre empezando a correr...")
                for (i in 1..10) {
                    println("Libre corriendo...")
                    Thread.sleep(100)
                }
            }
        } catch (e: InterruptedException){
            println("La liebre ha muerto")
        }
        println("Liebre ha llegado a la meta")
}


    val tortuga= Thread{
        println("Tortuga empezando a correr...")
        for ( i in 1..10){
            println("Tortuga corriendo...")
            Thread.sleep(1000)
        }
        println("Tortuga ha llegado a la meta")
    }

    liebre.start()
    tortuga.start()
    tortuga.join()
    liebre.join()
    Thread.sleep(5000)
    liebre.interrupt()//para interrumpir
}