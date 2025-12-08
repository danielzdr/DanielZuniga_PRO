package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import java.net.Socket

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val context = LocalContext.current
                    Posicion(context)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Posicion(context: Context) {
    // Lista de IPs: reemplaza por las IPs reales de tus 7 compañeros
    val defaultIps = listOf(
        "192.168.2.104",
        "192.168.2.149",
        "192.168.2.176",
        "192.168.2.180",
        "192.168.2.178",
        "192.168.2.17",
        "192.168.2.112",
        "192.168.2.241"
    )

    val ips = remember { mutableStateListOf<String>().apply { addAll(defaultIps) } }
    val selected = remember { mutableStateMapOf<String, Boolean>().apply { ips.forEach { put(it, false) } } }

    var touchPosition by remember { mutableStateOf(Pair(0f, 0f)) }
    var initialTouchPosition by remember { mutableStateOf(Pair(0f, 0f)) }
    val scope = rememberCoroutineScope()
    val port = 6000

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialTouchPosition = Pair(it.x, it.y)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        touchPosition = Pair(it.x, it.y)
                        val direction = getDirection(touchPosition, initialTouchPosition)
                        if (direction != null) {
                            // Envía a los seleccionados
                            val targets = selected.filter { it.value }.keys.toList()
                            if (targets.isNotEmpty()) {
                                scope.launch {
                                    for (host in targets) {
                                        sendDirectionToServer(host, port, direction)
                                    }
                                }
                                // Reiniciar referencia para evitar envíos continuos pequeños
                                initialTouchPosition = touchPosition
                            }
                        }
                    }
                }
                true
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Pos: ${"%.0f".format(touchPosition.first)}, ${"%.0f".format(touchPosition.second)}")
        }

        Divider()

        Text("Selecciona destinatarios (IPs):", modifier = Modifier.padding(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(ips.size) { idx ->
                val ip = ips[idx]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .toggleable(
                            value = selected[ip] ?: false,
                            onValueChange = { checked -> selected[ip] = checked }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = selected[ip] ?: false, onCheckedChange = { selected[ip] = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(ip)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                // Seleccionar todos
                ips.forEach { selected[it] = true }
            }) { Text("Seleccionar todos") }

            Button(onClick = {
                // Deseleccionar todos
                ips.forEach { selected[it] = false }
            }) { Text("Deseleccionar") }
        }
    }
}

// threshold y lógica de dirección
fun getDirection(touchPosition: Pair<Float, Float>, initialPosition: Pair<Float, Float>): String? {
    val deltaX = touchPosition.first - initialPosition.first
    val deltaY = touchPosition.second - initialPosition.second
    val threshold = 50f

    return when {
        deltaX > threshold -> "derecha"
        deltaX < -threshold -> "izquierda"
        deltaY > threshold -> "abajo"
        deltaY < -threshold -> "arriba"
        else -> null
    }
}

// función suspend para enviar dirección (añadimos '\n' y timeout)
suspend fun sendDirectionToServer(host: String, port: Int, direction: String, timeoutMs: Int = 2000) {
    withContext(Dispatchers.IO) {
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress(host, port), timeoutMs)
            socket.getOutputStream().use { os ->
                val mensaje = "$direction\n"
                os.write(mensaje.toByteArray())
                os.flush()
            }
            socket.close()
        } catch (e: Exception) {
            Log.e("Client", "Error enviando a $host:$port -> ${e.message}")
        }
    }
}
