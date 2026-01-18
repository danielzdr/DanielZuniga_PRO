import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.FileOutputStream

fun main() {
    val ftp= FTPClient()
    try {
        ftp.connect("127.0.0.1",21)
        ftp.login("dam2","RISA")
        ftp.enterLocalPassiveMode()
        ftp.setFileType(FTP.BINARY_FILE_TYPE)

        val output= FileOutputStream("C:\\Users\\Usuario\\Desktop\\prueba\\prueba.txt")
        ftp.retrieveFile("/prueba/pablo.txt",output)
        output.close()
    }catch (e: Exception){
        e.printStackTrace()

    }finally {
        ftp.logout()
        ftp.disconnect()
    }
}