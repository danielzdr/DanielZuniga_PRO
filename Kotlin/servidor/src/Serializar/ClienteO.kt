package Serializar

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ClienteO {
    fun enviarObjeto(persona: Persona){
        val cliente=Socket("127.0.0.1",1238)
        val out= ObjectOutputStream(cliente.getOutputStream())
        out.writeObject(persona)
        out.flush()
        out.close()
        cliente.close()
    }
}