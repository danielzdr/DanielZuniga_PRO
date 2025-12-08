package puertosAbieros

import java.io.IOException
import java.net.Socket
import java.net.UnknownHostException

fun main() {
    try {
        val cliente = Socket("192.168.2.216", 80)
        println("Ha funcionado")
    }catch (e: UnknownHostException){
        println(e)
    }catch (e: IOException){
        println("No he podido abir el puerto")
    }
}