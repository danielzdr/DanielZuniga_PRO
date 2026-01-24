package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.MainActivity
import com.example.agendajson.R
import com.example.agendajson.databinding.ItemUserBinding
import com.example.agendajson.dataset.DataSet
import com.example.agendajson.model.User

class AdapterUser(var contexto: Context)
    : RecyclerView.Adapter<AdapterUser.MyHolder>() {
    private var lista: ArrayList<User>
    private lateinit var listener: OnUserClickListener



    inner class MyHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.toolbarCard.inflateMenu(R.menu.menu_card)
            binding.toolbarCard.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_ver_detalles->{
                        //Llamar y que se quede para siemore el listener en MainActivity
                        listener.onUserDetalles(lista[bindingAdapterPosition])
                    }
                    R.id.menu_agregar->{
                        //Agregar a favoritos los usuarios
                        DataSet.agregarUsersFavs(lista[bindingAdapterPosition])
                        Toast.makeText(contexto, "Usuario agregado a favoritos", Toast.LENGTH_SHORT).show()
                    }
                }

                return@setOnMenuItemClickListener true }
        }
    }

        init {
            lista = ArrayList()
            listener=contexto as MainActivity
        }

        fun clearUsers() {
        lista.clear()
        notifyDataSetChanged()
        }

        fun addUser(user: User): Unit {
            this.lista.add(user)
            notifyItemInserted(this.lista.size - 1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MyHolder(binding)
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            val user = lista[position]
            holder.binding.toolbarCard.title = user.firstName
            holder.binding.textNombre.text= user.lastName
            holder.binding.textMail.text = user.email
            Glide.with(contexto)
                .load(user.image)
                .into(holder.binding.imageView)


            }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface OnUserClickListener {
        fun onUserDetalles(user: User)
    }
    }



