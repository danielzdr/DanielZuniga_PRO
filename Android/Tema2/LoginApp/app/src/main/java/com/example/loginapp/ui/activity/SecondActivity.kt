package com.example.loginapp.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.R
import com.example.loginapp.databinding.ActivitySecondBinding
import com.example.loginapp.model.Usuario

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
     var usuario: Usuario?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        usuario=intent.getSerializableExtra("usuario") as Usuario
        binding.textoSaludo.text = "Bienvenido ${usuario?.correo}"
        binding.textoContra.text = "Con contraseña ${usuario?.pass}"
        binding.textoPlataforma.text="Sesion iniciada con ${usuario?.plataforma}"
        val imagen=intent.getIntExtra("facebook",2)
        binding.ImagenPlat.setImageResource(imagen)


    }
}