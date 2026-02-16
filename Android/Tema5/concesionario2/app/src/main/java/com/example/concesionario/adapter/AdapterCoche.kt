package com.example.concesionario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.concesionario.databinding.CardCocheBinding
import com.example.concesionario.model.Coche

class AdapterCoche(var context: Context): RecyclerView.Adapter<AdapterCoche.MyHolder>(){
    private lateinit var lista: ArrayList<Coche>
    inner class MyHolder(var binding: CardCocheBinding): RecyclerView.ViewHolder(binding.root){
       init {
          lista= ArrayList()
       }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = CardCocheBinding.inflate( LayoutInflater.from(parent.context) ,parent, false )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val coche=lista[position]
        holder.binding.textMarca.text=coche.marca
        holder.binding.textModelo.text=coche.modelo
        holder.binding.textColor.text=coche.color
        holder.binding.textId.text=coche.id.toString()


    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addCoche(x: Coche){
        lista.add(x)
        notifyItemInserted(lista.size-1)

    }


}