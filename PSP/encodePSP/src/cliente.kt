import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

fun main() {
    try {
        val registro: Registry = LocateRegistry.getRegistry("172.20.10.3", 2020)
        val sumaRemota = registro.lookup("sumaRemota") as SumaRemota
        println("Suma de 5 + 3 = ${sumaRemota.suma(5, 3)}")
    }catch (e: Exception) {
        e.printStackTrace()
    }
}