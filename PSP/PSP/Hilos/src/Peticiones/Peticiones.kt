package Peticiones


import okhttp3.OkHttpClient
import okhttp3.Request

fun httpGetOk(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            throw Exception("Error HTTP: ${response.code}")
        }
        return response.body?.string() ?: ""
    }
}

fun main() {
    val url = "http://192.168.2.216"
    httpGetOk(url)
}