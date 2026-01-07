package com.example.tienda.ui.activitys

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterCarrito
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.ActivityProductosBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.dialogs.DialogoProducto

class ProductosActivity : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener{

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

        binding.recyclerCarrito.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,false)
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
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun actualizarContadorCarrito() {

    }

    override fun onProductoSeleccionado(producto: Producto) {

    }

    /*override fun onConfirmarCompra() {
        Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show()
        DataSet.listaCarrito.clear()
        adapterCarrito.notifyDataSetChanged()
        calcularTotal()
        DialogoProducto().show(supportFragmentManager, "dialogo")
    }*/
}