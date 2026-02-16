package com.example.examen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.databinding.CardImagenBinding
import com.example.examen.databinding.CardImagenFavsBinding
import com.example.examen.model.Elemento

class AdapterFavs(var context: Context): RecyclerView.Adapter<AdapterFavs.ViewHolder>() {

    private lateinit var favoritos: ArrayList<Elemento>
    inner class ViewHolder(var binding: CardImagenFavsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            favoritos = ArrayList()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(CardImagenFavsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val elemento = favoritos[position]
        holder.binding.textId.text = elemento.id.toString()
        Glide.with(context)
            .load(elemento.imagen)
            .into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
        return favoritos.size
    }

    fun addElementoFavs(elemento: Elemento){
        favoritos.add(elemento)
        notifyItemInserted(favoritos.size - 1)
    }

}