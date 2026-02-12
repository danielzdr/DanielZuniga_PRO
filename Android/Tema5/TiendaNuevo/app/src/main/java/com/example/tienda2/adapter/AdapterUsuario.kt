package com.example.tienda2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda2.R
import com.example.tienda2.databinding.CardUserBinding
import com.example.tienda2.model.Usuario

class AdapterUsuario(context: Context): RecyclerView.Adapter<AdapterUsuario.MyHolder>() {
    private lateinit var lista: ArrayList<Usuario>

    inner class MyHolder(var binding: CardUserBinding) : RecyclerView.ViewHolder(binding.root){

    init {
        binding.toolbarCard.inflateMenu(R.menu.menu_card)
        binding.toolbarCard.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_detalle -> {

                }

                R.id.menu_agregar -> {

                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    init {
        lista = ArrayList()
    }
}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = CardUserBinding.inflate( LayoutInflater.from(parent.context)
    ,parent, false )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val user=lista[position]
        holder.binding.toolbarCard.title=user.nombre
        holder.binding.textApellido.text=user.apellido
        holder.binding.textMail.text=user.email
        holder.binding.textPass.text=user.password
        holder.binding.textEdad.text=user.edad.toString()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun addUser(x: Usuario) {
        lista.add(x)
        notifyItemInserted(lista.size-1)

    }
}
