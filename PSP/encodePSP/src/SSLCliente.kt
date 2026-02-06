import javax.net.ssl.SSLSocketFactory

fun main() {
    System.setProperty("javax.net.ssl.trustStore","Almacen")
    System.setProperty("javax.net.ssl.keystorePassword","1234567")

    val socketFactory= SSLSocketFactory.getDefault() as SSLSocketFactory
    val cliente=socketFactory.createSocket("127.0.0.1",2000)
}