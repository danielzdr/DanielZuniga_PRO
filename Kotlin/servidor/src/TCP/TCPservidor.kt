package TCP

import jdk.swing.interop.DispatcherWrapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class TCPservidor {
    fun startServer(){
        try {
            val server=ServerSocket(5000)
            while (true) {
                val cliente = server.accept()
                val leer = BufferedReader(InputStreamReader(cliente.getInputStream()))
                val escribir = PrintWriter(cliente.getOutputStream(), true)
                var mensaje: String?
                do {
                    mensaje = leer.readLine()
                    println(mensaje)
                    escribir.print("mensaje recibido")
                } while (mensaje != null && mensaje != "fin")
                //si esto no se cierra error bind
                leer.close()
                escribir.close()
                cliente.close()
                server.close()
            }
        }catch (e:Exception){

        }
    }

    private fun launch(io: Any) {

    }
}

fun main() {
    val server = TCPservidor().startServer()
}