package TCP

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class TCPcliente {
    fun startCliente(){
        try {
            val cliente=Socket("127.0.0.1",5000)
            val leer= BufferedReader(InputStreamReader(cliente.getInputStream()))
            val escribir= PrintWriter(cliente.getOutputStream(),true)
            var mensaje:String?
            do {
                print("escribe un mensaje: ")
                mensaje= readLine()
                escribir.write(mensaje)
                print(leer.readLine())
            }while (mensaje!=null && mensaje!="fin")
            leer.close()
            escribir.close()
            cliente.close()
        }catch (e:Exception){

        }
    }
}

fun main() {
    val cliente=TCPcliente().startCliente()
}