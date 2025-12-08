package com.example.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etDeviceId: EditText
    private lateinit var btnCES: Button
    private lateinit var btnEstocolmo: Button
    private lateinit var btnPraga: Button

    // Cambia esto por la IP de tu PC
    private val baseUrl = "http://192.168.2.174:9090/"
    private val apiService = ApiClient.getClient(baseUrl).create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDeviceId = findViewById(R.id.etDeviceId)
        btnCES = findViewById(R.id.btnCES)
        btnEstocolmo = findViewById(R.id.btnEstocolmo)
        btnPraga = findViewById(R.id.btnPraga)

        btnCES.setOnClickListener { sendPosition(40.3478, -3.8277) }
        btnEstocolmo.setOnClickListener { sendPosition(40.3479, -3.8276) }
        btnPraga.setOnClickListener { sendPosition(40.3477, -3.8278) }
    }

    private fun sendPosition(lat: Double, lon: Double) {
        val deviceId = etDeviceId.text.toString()
        if (deviceId.isEmpty()) {
            Toast.makeText(this, "Introduce el Device ID", Toast.LENGTH_SHORT).show()
            return
        }

        val ts = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(Date())
        val pos = Position(deviceId, lat, lon, ts)

        apiService.sendPosition(pos).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Ubicación enviada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Error al enviar ubicación", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fallo de conexión: ${t.message}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }
        })
    }
}

