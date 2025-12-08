package UDP

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    val datagrama=DatagramSocket(1234)
    while (true) {
        val buffer = ByteArray(1024)
        val paqueterespuesta = DatagramPacket(buffer, buffer.size)
        print(datagrama.receive(paqueterespuesta))
        val cadenaFinal = String(paqueterespuesta.data, 0, paqueterespuesta.length)
        println(cadenaFinal)

    }
}
