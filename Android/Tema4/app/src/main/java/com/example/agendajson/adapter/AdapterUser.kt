package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.databinding.ItemUserBinding
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.User

class AdapterUser(var contexto: Context)
    : RecyclerView.Adapter<AdapterUser.MyHolder>() {

    private var lista: ArrayList<User>
    private var onItemClickListener: ((User) -> Unit)? = null

    inner class MyHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
        init {
            this.lista = ArrayList()
        }

    fun setOnItemClickListener(listener: (User) -> Unit) {
        this.onItemClickListener = listener
    }

    fun clearUsers() {
        val size = lista.size
        lista.clear()
        notifyItemRangeRemoved(0, size)
    }


        fun updateUsers(newList: List<User>) {
        lista.clear()
        lista.addAll(newList)
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

            holder.binding.textNombre.text = user.firstName
            holder.binding.textMail.text = user.email

            Glide.with(contexto)
                .load(user.image)
                .into(holder.binding.imageView)


        }

        override fun getItemCount(): Int {
            return lista.size
        }

    }
