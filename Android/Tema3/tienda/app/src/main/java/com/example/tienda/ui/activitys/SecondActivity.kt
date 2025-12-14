package com.example.tienda.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.databinding.ActivitySecondBinding
import com.example.tienda.model.Producto

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        acciones()

    }
    private fun acciones() {
        val producto= intent.getSerializableExtra("Producto") as Producto

        binding.textId.id= producto.id
        binding.textNombre.text=producto.nombre
        binding.textStock.text=producto.stock.toString()
        binding.textPrecio.text=producto.precio.toString()
        binding.textDescripcion.text=producto.descripcion
        binding.textCategoria.text=producto.categoria

    }
}