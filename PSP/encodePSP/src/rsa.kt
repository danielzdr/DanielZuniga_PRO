import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.security.*
import java.util.Base64
import java.nio.file.*

fun main() {
    val key = generarRSA()
    guardar("publica.der", key.public.encoded)
    guardar("privada.der", key.private.encoded)

    val publica = cargarPublico("publica.der")
    val privada = cargarPrivada("privada.der")

    println(Base64.getEncoder().encodeToString(publica.encoded))
    println(Base64.getEncoder().encodeToString(privada.encoded))
}

fun guardar(file: String, bytes: ByteArray) = Files.write(Paths.get(file), bytes)

fun generarRSA(): KeyPair = KeyPairGenerator.getInstance("RSA").apply {
    initialize(2048)
}.generateKeyPair()

fun cargarPublico(file: String): PublicKey {
    val contenido = Files.readAllBytes(Paths.get(file))
    return KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(contenido))
}

fun cargarPrivada(file: String): PrivateKey {
    val contenido = Files.readAllBytes(Paths.get(file))
    return KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(contenido))
}