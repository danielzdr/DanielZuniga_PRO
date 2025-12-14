package com.example.tienda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tienda.R
import com.example.tienda.databinding.ItemCarritoBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto

class AdapterCarrito(var listaCarrito: ArrayList<Producto>) :
    RecyclerView.Adapter<AdapterCarrito.CarritoHolder>() {

    inner class CarritoHolder(var binding: ItemCarritoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoHolder {
        val binding = ItemCarritoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarritoHolder(binding)
    }

    override fun onBindViewHolder(holder: CarritoHolder, position: Int) {
        val producto = listaCarrito[position]

        holder.binding.tvNombreProducto.text = producto.nombre
        holder.binding.tvPrecioProducto.text = "$${producto.precio}"

        // Cargar imagen si es necesario
        Glide.with(holder.itemView.context)
            .load(producto.imagen)
            .placeholder(R.drawable.producto)
            .into(holder.binding.ivProducto)

        holder.binding.btnEliminar.setOnClickListener {
            listaCarrito.remove(producto)
            DataSet.listaCarrito.remove(producto)
            notifyItemRemoved(position-1)
            //notifyItemRangeChanged(position, listaCarrito.size)
        }
    }

    override fun getItemCount(): Int = listaCarrito.size
}