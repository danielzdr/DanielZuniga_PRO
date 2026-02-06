// Cliente.kt
import java.io.*
import java.net.Socket
import java.security.*
import java.security.spec.X509EncodedKeySpec
import java.time.LocalDateTime
import java.util.*
import javax.crypto.Cipher

fun main() {
    println("=== CLIENTE RSA ===")
    val ipServidor = obtenerIpServidor()
    val puerto = 12345

    val clavePublicaServidor = cargarClavePublicaServidor()

    println("\n🔗 Conectando a $ipServidor:$puerto...")

    try {
        val socket = Socket(ipServidor, puerto)
        println("✅ Conexión establecida")

        val input = BufferedReader(InputStreamReader(socket.getInputStream()))
        val output = PrintWriter(socket.getOutputStream(), true)


        println("Servidor: ${input.readLine()}")

        while (true) {
            println("\n" + "=".repeat(50))
            println("OPCIONES:")
            println("1. Enviar mensaje cifrado")
            println("2. Enviar mensaje de prueba")
            println("3. Salir")
            print("Selecciona opción: ")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> {
                    print("Escribe tu mensaje: ")
                    val mensaje = readlnOrNull() ?: ""
                    if (mensaje.isNotBlank()) {
                        enviarMensajeCifrado(mensaje, clavePublicaServidor, output, input)
                    }
                }
                2 -> {
                    val mensajePrueba = "Hola servidor! Este es un mensaje secreto. " +
                            "Fecha: ${LocalDateTime.now()}"
                    println("Enviando mensaje de prueba...")
                    enviarMensajeCifrado(mensajePrueba, clavePublicaServidor, output, input)
                }
                3 -> {
                    output.println("exit")
                    println("Desconectando...")
                    break
                }
                else -> println("Opción no válida")
            }
        }

        socket.close()
        println("✅ Cliente cerrado")

    } catch (e: Exception) {
        println("❌ Error de conexión: ${e.message}")
        println("Asegúrate de que el servidor esté ejecutándose en $ipServidor:$puerto")
    }
}

fun obtenerIpServidor(): String {
    println("\n🔧 CONFIGURACIÓN DE CONEXIÓN")
    println("IPs disponibles en tu red:")


    val ipLocal = java.net.InetAddress.getLocalHost().hostAddress
    println("   - Tu IP local: $ipLocal")
    println("   - localhost: 127.0.0.1 (para pruebas en la misma máquina)")

    print("\nIntroduce la IP del servidor [localhost]: ")
    val ip = readlnOrNull()?.trim()

    return if (ip.isNullOrBlank()) "localhost" else ip
}

fun cargarClavePublicaServidor(): PublicKey {
    println("\n🔑 CARGAR CLAVE PÚBLICA DEL SERVIDOR")
    println("1. Cargar desde archivo 'publica_servidor.txt'")
    println("2. Pegar clave pública en Base64")
    println("3. Usar clave de ejemplo (solo para pruebas)")
    print("Selecciona opción: ")

    val opcion = readlnOrNull()?.toIntOrNull() ?: 1

    return when (opcion) {
        1 -> {
            try {
                val archivo = "publica_servidor.txt"
                val base64Key = File(archivo).readText().trim()
                cargarClaveDesdeBase64(base64Key)
            } catch (e: Exception) {
                println("❌ Error: ${e.message}")
                println("Usando opción 2...")
                println("Pega la clave pública en Base64:")
                val base64Key = readlnOrNull()?.trim() ?: ""
                cargarClaveDesdeBase64(base64Key)
            }
        }
        2 -> {
            println("Pega la clave pública en Base64 (sin espacios):")
            print("> ")
            val base64Key = readlnOrNull()?.trim() ?: ""
            cargarClaveDesdeBase64(base64Key)
        }
        3 -> {
            println("⚠️  Usando clave de ejemplo (NO usar en producción)")
            // Clave pública de ejemplo (RSA 512 bits para demo)
            val ejemploBase64 = """
                MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK6F3Nv12fqK1hmFM8S5h9l2RnGp5M1Z
                QHXwDgg3mZXg6u5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5J5
                CAwEAAQ==
            """.trimIndent().replace("\n", "")
            cargarClaveDesdeBase64(ejemploBase64)
        }
        else -> {
            println("Opción no válida, usando opción 1")
            cargarClavePublicaServidor()
        }
    }
}

fun cargarClaveDesdeBase64(base64Key: String): PublicKey {
    val bytes = Base64.getDecoder().decode(base64Key)
    return KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(bytes))
}

fun enviarMensajeCifrado(
    mensaje: String,
    clavePublica: PublicKey,
    output: PrintWriter,
    input: BufferedReader
) {
    try {
        println("\n📨 Preparando mensaje...")
        println("   Original: \"$mensaje\"")
        println("   Longitud: ${mensaje.length} caracteres")

        // Cifrar mensaje
        val mensajeCifradoBase64 = cifrarRSA(mensaje, clavePublica)
        println("   Cifrado: ${mensajeCifradoBase64.length} caracteres Base64")

        // Enviar al servidor
        output.println(mensajeCifradoBase64)
        println("   ✅ Enviado al servidor")

        // Esperar respuesta
        val respuesta = input.readLine()
        println("   Servidor: $respuesta")

    } catch (e: javax.crypto.IllegalBlockSizeException) {
        println("❌ Error: Mensaje demasiado largo para RSA")
        println("   Intenta con un mensaje más corto (máximo ~245 caracteres)")
    } catch (e: Exception) {
        println("❌ Error al cifrar/enviar: ${e.message}")
    }
}

fun cifrarRSA(texto: String, clavePublica: PublicKey): String {
    val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
    cipher.init(Cipher.ENCRYPT_MODE, clavePublica)

    val bytesTexto = texto.toByteArray(Charsets.UTF_8)
    val bytesCifrados = cipher.doFinal(bytesTexto)

    return Base64.getEncoder().encodeToString(bytesCifrados)
}