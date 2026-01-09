import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient

fun main() {
    val ftp= FTPClient()
    try {
        ftp.connect("ftp://ftp.rediris.es",21)
        ftp.login("anonymous","RISA")
        ftp.enterLocalPassiveMode()
        ftp.setFileType(FTP.BINARY_FILE_TYPE)

        val ficheros = ftp.listFiles("/sites")
        for (fichero in ficheros){
            if (fichero.isFile){
                println("Fichero: ${fichero.name}")
            }else if (fichero.isDirectory){
                println("Directorio: ${fichero.name}")
            }
        }
    }catch (e: Exception){
        println(e.message)
    }
}