import java.io.DataInputStream
import java.io.DataOutputStream
import javax.net.ssl.SSLServerSocketFactory

fun main() {

    System.setProperty("javax.net.ssl.keystore","Almacen")
    System.setProperty("javax.net.ssl.keystorePassword","1234567")
    val serverFactory= SSLServerSocketFactory.getDefault() as SSLServerSocketFactory
    val server=serverFactory.createServerSocket(2000)
    val cliente=server.accept()

    val entrada= DataInputStream(cliente.getInputStream())
    val salida = DataOutputStream(cliente.getOutputStream())
    entrada.close()
    salida.close()

}