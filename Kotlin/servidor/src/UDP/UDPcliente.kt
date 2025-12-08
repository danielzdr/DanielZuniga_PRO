package UDP

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    val datagrama= DatagramSocket(1234)
    val direccion= InetAddress.getByName("localhost")
    val paquete= DatagramPacket("hola".toByteArray(),"hola".toByteArray().size,direccion,1234)
    datagrama.send(paquete)
}