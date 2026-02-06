package com.example.tienda.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.databinding.CardUserBinding
import com.example.tienda.model.Usuario

class AdapterUser(var contexto: Context): RecyclerView.Adapter<AdapterUser.MyHolder>(){

    private lateinit var lista: ArrayList<Usuario>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding= CardUserBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val usuario= lista[position]
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(var binding: CardUserBinding): RecyclerView.ViewHolder(binding.root){

    }

    init {
        lista= ArrayList()

    }

    fun clearUsers() {
        lista.clear()
        notifyDataSetChanged()
    }


}