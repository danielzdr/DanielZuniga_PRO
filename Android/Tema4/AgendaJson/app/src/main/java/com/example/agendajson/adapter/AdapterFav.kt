package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.ItemUserBinding
import com.example.agendajson.databinding.ItemUserFavBinding
import com.example.agendajson.model.User

class AdapterFav(var contexto: Context): RecyclerView.Adapter<AdapterFav.MyHolder>() {
    private var listaUsuarios: ArrayList<User>
    private lateinit var listener: OnUserClickListener


    inner class MyHolder (var binding: ItemUserFavBinding): RecyclerView.ViewHolder(binding.root){
     init {
         binding.toolbarCard2.inflateMenu(R.menu.menu_card_favs)
         binding.toolbarCard2.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_eliminar_fav->{
                        //Eliminar de favoritos los usuarios
                        val posicion= bindingAdapterPosition
                        listaUsuarios.removeAt(posicion)
                        notifyItemRemoved(posicion)
                    }
                    R.id.menu_ver_detalles->{
                        //Llamar y que se quede para siemore el listener en MainActivity
                        listener.onUserDetalles(listaUsuarios[bindingAdapterPosition])


                    }
                }

                return@setOnMenuItemClickListener true
         }
     }

    }
    init {
        listaUsuarios= ArrayList()
        listener= contexto as OnUserClickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = ItemUserFavBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val user = listaUsuarios[position]
        holder.binding.toolbarCard2.title = user.firstName
        holder.binding.textNombre2.text= user.lastName
        holder.binding.textMail2.text = user.email
        Glide.with(contexto)
            .load(user.image)
            .into(holder.binding.imageView2)
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
    fun clearUsers() {
        listaUsuarios.clear()
        notifyDataSetChanged()
    }

    interface OnUserClickListener {
        fun onUserDetalles(user: User)
    }


}