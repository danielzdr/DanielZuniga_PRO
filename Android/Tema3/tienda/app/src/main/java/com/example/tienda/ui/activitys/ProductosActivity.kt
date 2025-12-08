package com.example.tienda.ui.activitys

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterCarrito
import com.example.tienda.databinding.ActivityProductosBinding
import com.example.tienda.dataset.DataSet

class ProductosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductosBinding
    private lateinit var adapterCarrito: AdapterCarrito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializar RecyclerView
        adapterCarrito = AdapterCarrito(DataSet.Companion.listaCarrito)

        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapterCarrito

        calcularTotal()

        binding.btnComprar.setOnClickListener {
            realizarCompra()
        }
    }

    private fun calcularTotal() {
        var total = 0.0
        for (producto in DataSet.Companion.listaCarrito) {
            total += producto.precio
        }
        binding.tvTotal.text = "Total: $${String.format("%.2f", total)}"
    }

    private fun realizarCompra() {
        if (DataSet.Companion.listaCarrito.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle("Confirmar compra")
            .setMessage("¿Desea confirmar la compra de ${DataSet.Companion.listaCarrito.size} productos?")
            .setPositiveButton("Sí") { dialog, which ->
                // Aquí iría la lógica de compra
                Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()
                DataSet.Companion.listaCarrito.clear()
                adapterCarrito.notifyDataSetChanged()
                calcularTotal()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}