import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

fun main() {
    val input = BufferedReader(InputStreamReader(System.`in`))
    val numeroAleatorio= input.readLine()?.toInt() ?:return

    println("Hijo: He recibido el numero del padre. ¡Vamos a adivinarlo!")
    var adivinado=false
    while (!adivinado){
        val intento= Random.nextInt(1,51)
        println("Intento con el numero $intento")

        if (intento==numeroAleatorio){
            println("¡Numero acertado!, Has ganado el juego de adivinar el numero $intento")
            adivinado=true
        }else{
            println("Numero Erroneo $intento")
        }
    }

    //metodo que sirve para no ejecutar mas instrucciones y permiten
    //que otros procesos pueden usar la CPU
    //dar tiempo entre cada intento de adivinar el numero
    //simular que hijo piensa un poco antes de cada intento
    Thread.sleep(500)

}