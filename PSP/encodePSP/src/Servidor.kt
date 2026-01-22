import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry


fun main() {
    val sumaRemota = SumaRemotaImp()
    try {
        val registro: Registry = LocateRegistry.createRegistry(2020)
        registro.rebind("sumaRemota", sumaRemota)
        println("Servidor RMI listo y esperando conexiones...")
    }catch (e: Exception) {
        e.printStackTrace()
    }
}