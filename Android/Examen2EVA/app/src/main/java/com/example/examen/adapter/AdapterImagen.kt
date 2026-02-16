package com.example.examen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen.databinding.CardImagenBinding
import com.example.examen.model.Elemento

class AdapterImagen(var context: Context): RecyclerView.Adapter<AdapterImagen.ViewHolder>() {

    private lateinit var imagen: ArrayList<Elemento>
    inner class ViewHolder(var binding: CardImagenBinding): RecyclerView.ViewHolder(binding.root){
        init{

        }

        init {
            imagen= ArrayList()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(CardImagenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        var elemento = imagen[position]
        holder.binding.textId.text = elemento.id.toString()
        Glide.with(context)
            .load(elemento.imagen)
            .into(holder.binding.imageView)
        holder.binding.botonFav.setOnClickListener {
            // Agregar a favoritos
            //funcionalidad para agregar a favoritos, por ejemplo, podrías usar una base de datos local o Firebase para almacenar los elementos favoritos del usuario logeado



        }
    }

    override fun getItemCount(): Int {
        return imagen.size
    }

    fun addElemento(elemento: Elemento){
        imagen.add(elemento)
        notifyItemInserted(imagen.size - 1)
    }

}