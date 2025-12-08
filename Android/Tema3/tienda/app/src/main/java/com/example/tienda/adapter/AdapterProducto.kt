package com.example.tienda.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tienda.R
import com.example.tienda.databinding.ItemProductoBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.activitys.SecondActivity
import com.google.android.material.snackbar.Snackbar

class AdapterProducto(var lista: ArrayList<Producto>, var contexto: Context) :
    RecyclerView.Adapter<AdapterProducto.MyHolder>() {
    var listener: OnProductoCarritoListener

    init {
        listener = contexto as OnProductoCarritoListener
    }

    inner class MyHolder(var binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemProductoBinding.inflate(
            LayoutInflater.from(contexto),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val producto: Producto = lista[position]

        // Cargar imagen con Glide
        Glide.with(contexto)
            .load(producto.imagen)
            .placeholder(R.drawable.producto)
            .error(R.drawable.producto)
            .centerCrop()
            .into(holder.binding.imagenFila)

        holder.binding.textoNombre.text = producto.nombre

        holder.binding.botonDetalle.setOnClickListener {
            val intent = Intent(contexto, SecondActivity::class.java)
            intent.putExtra("producto", producto)
            contexto.startActivity(intent)
        }

        holder.binding.botonAgregar.setOnClickListener {
            DataSet.addProducto(producto)
            listener.actualizarContadorCarrito()

            // Feedback visual
            Toast.makeText(contexto, "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = lista.size

    fun chageList(nuevaLista: ArrayList<Producto>){
        lista.clear()
        lista.addAll(nuevaLista)
        notifyDataSetChanged()

        Log.d("Adapter", "Lista actualizada: ${lista.size} productos")
    }

    interface OnProductoCarritoListener {
        fun actualizarContadorCarrito()
    }
}


