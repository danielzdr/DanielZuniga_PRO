
import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject
import kotlin.jvm.Throws

class SumaRemotaImp: UnicastRemoteObject(), SumaRemota {
    @Throws(RemoteException::class)
    override fun suma(a: Int, b: Int): Int {
        return a + b
    }

}