package com.example.futbol.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futbol.R
import com.example.futbol.databinding.ListadoFragmentBinding
import com.example.futbol.dataset.DataSet
import com.example.futbol.model.Ligas

class AdapterListado(var context: Context):RecyclerView.Adapter<AdapterListado.MyHolder>() {
    private var lista: MutableList<Ligas> = mutableListOf()
    inner class MyHolder(var binding: ListadoFragmentBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.toolbarLigas.inflateMenu(R.menu.menu_listado)
            when(binding.toolbarLigas.menu.findItem(R.id.menu_favoritos).itemId) {
                R.id.menu_favoritos -> {
                    DataSet.añadirFavoritos(lista[adapterPosition].nombreLiga)
                }
                    // Acción para el item "Liga"
                }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding=ListadoFragmentBinding.inflate(
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
        val ligas =lista[position]
        holder.binding.toolbarLigas.title=ligas.nombreLiga

    }

    override fun getItemCount(): Int {
        return lista.size
    }



}