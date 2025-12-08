package url

import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    val cliente=OkHttpClient()
    val url=  "https://192.168.2.216/"
    val peticion=Request.Builder().url(url).build()
    val respuesta= cliente.newCall(peticion).execute()
    println(respuesta.body?.string())
}