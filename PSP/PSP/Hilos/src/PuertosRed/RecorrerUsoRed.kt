package PuertosRed
import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.Duration

fun main() {
    val client = OkHttpClient.Builder()
        .callTimeout(Duration.ofSeconds(5))
        .build()

    val ip = "192.168.2.216"
    val url = "http://$ip:80/"

    val request = Request.Builder()
        .url(url)
        .header("Admin", "Kotlin-HealthCheck/1.0")
        .build()

    client.newCall(request).execute().use { response ->
        println("Código HTTP: ${response.code}")
        val body = response.body?.string()
        if (body != null) {
            println("Contenido (primeros 1000 chars):")
            println(body.take(1000))
        } else {
            println("Sin contenido en la respuesta.")
        }
    }
}