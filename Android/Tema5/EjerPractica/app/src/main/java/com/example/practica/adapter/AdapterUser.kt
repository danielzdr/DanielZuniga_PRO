package com.example.practica.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practica.R
import com.example.practica.databinding.CardUserBinding
import com.example.practica.model.Usuario
import com.google.android.material.snackbar.Snackbar

class AdapterUser(var contexto: Context)
    : RecyclerView.Adapter<AdapterUser.MyHolder>() {
    private var lista: MutableList<Usuario> = mutableListOf()
    private lateinit var listener: onUserClickListener

    inner class MyHolder(var binding: CardUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.toolbarCard.inflateMenu(R.menu.menu_card)
            binding.toolbarCard.setOnMenuItemClickListener {
                when(it.itemId){

                    R.id.menu_ver_detalles->{
                        val bundle: Bundle= Bundle()
                        bundle.putSerializable("usuario", lista[adapterPosition])
                        findNavController(binding.root).navigate(R.id.action_loginFragment_to_dialogoDetalleUsuario,bundle)
                    }
                    R.id.menu_apellido->{
                        Snackbar.make(binding.root, "Apellido: ${lista[adapterPosition].apellido}", Snackbar.LENGTH_SHORT).show()
                    }
                }
                return@setOnMenuItemClickListener true
            }
            }
        init {
            lista=mutableListOf()
            listener=contexto as onUserClickListener
        }
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
        //Glide.with(contexto)
           // .load(user.imagen)
            //.into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface onUserClickListener{
        fun onUserDetalles(usuario: Usuario)
    }
}

