package url

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI

fun main() {
    val url = URI("http://countries.trevorblades.com/").toURL()
    val query="""
        {
        "query: {query {
  	countries{
      name
      code
    }
}
}
        }
        """.trimIndent()

    val conexion=url.openConnection() as HttpURLConnection
    conexion.requestMethod="POST"
    conexion.setRequestProperty("Content-Type", "application/x-www-form-urlenencoded")
    conexion.doOutput=true
    val out=conexion.outputStream
    out.write(query.toByteArray())
    out.flush()
    out.close()

    val input=conexion.inputStream
    val leer= BufferedReader(InputStreamReader(input))
    var linea:String?
    while (leer.readLine().also { linea=it }!=null)
    {
        println(linea)
    }
}