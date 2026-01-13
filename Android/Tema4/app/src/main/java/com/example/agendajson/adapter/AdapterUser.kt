package com.example.agendajson.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.ItemUserBinding
import com.example.agendajson.model.User

class AdapterUser(var listaUser:ArrayList<User>, var contexto: Context):
    RecyclerView.Adapter<AdapterUser.MyHolder>() {

    var listener: OnUserListener

    init {
        listener=contexto as OnUserListener
    }
    inner class MyHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = ItemUserBinding.inflate(
            android.view.LayoutInflater.from(contexto),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterUser.MyHolder, position: Int) {
        val user: User = listaUser[position]
        Glide.with(contexto)
            .load(user.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imageView)

        holder.binding.textNombre.text = user.firstName.toString()
        holder.binding.textMail.text = user.email.toString()
    }

    override fun getItemCount(): Int {
        return listaUser.size
    }

    interface OnUserListener {
        fun onUserSeleccionado(user: User)
    }

    fun setDatos(nuevosUsers: ArrayList<User>) {
        listaUser.clear()
        listaUser.addAll(nuevosUsers)
        notifyDataSetChanged()
    }
}