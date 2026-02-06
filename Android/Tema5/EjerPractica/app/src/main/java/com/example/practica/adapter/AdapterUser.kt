package com.example.practica.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practica.R
import com.example.practica.databinding.CardUserBinding
import com.example.practica.model.Usuario

class AdapterUser(var contexto: Context)
    : RecyclerView.Adapter<AdapterUser.MyHolder>() {
    private var lista: MutableList<Usuario> = mutableListOf()

    inner class MyHolder(var binding: CardUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            //binding.toolbarCard.inflateMenu(R.menu.menu_card)
        }
    }

    fun clearUsers() {
        lista.clear()
        notifyDataSetChanged()
    }

    fun addUsuario(user: Usuario) {
        this.lista.add(user)
        notifyItemInserted(this.lista.size - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterUser.MyHolder {
        val binding = CardUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterUser.MyHolder, position: Int) {
        val user =lista[position]
        holder.binding.toolbarCard.title=user.nombre
        holder.binding.textApellido.text=user.apellido
        holder.binding.textMail.text=user.email
        holder.binding.textPass.text=user.password
        Glide.with(contexto)
            .load(user.imagen)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}

