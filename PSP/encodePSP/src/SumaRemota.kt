import java.rmi.Remote
import java.rmi.RemoteException
import kotlin.jvm.Throws

interface SumaRemota: Remote {
    @Throws(RemoteException::class)
    fun suma(a: Int, b: Int): Int
}