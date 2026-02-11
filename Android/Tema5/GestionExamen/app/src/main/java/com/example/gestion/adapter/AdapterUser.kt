package com.example.gestion.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestion.R
import com.example.gestion.databinding.CardUserBinding
import com.example.gestion.model.Examen
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.os.Bundle
import androidx.navigation.Navigation.findNavController

class AdapterUser(var context: Context): RecyclerView.Adapter<AdapterUser.ViewHolder>() {
    private lateinit var examenes: MutableList<Examen>
    private lateinit var examen: Examen
    inner class ViewHolder(var binding: CardUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.toolbarCard.inflateMenu(R.menu.menu_card)
            binding.toolbarCard.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_ver_detalles -> {
                        // Acción para la opción 1
                        val bundle: Bundle = Bundle()
                        bundle.putSerializable("examen", examenes[adapterPosition])
                        findNavController(binding.root).navigate(R.id.action_fragmentListado_to_fragmentDetalle,bundle)

                    }
                    R.id.menu_dificultad -> {
                        // Acción para la opción 2
                        Snackbar.make(binding.root, "Dificultad: ${examenes[adapterPosition]}", Snackbar.LENGTH_LONG).show()

                    }

                }
                return@setOnMenuItemClickListener true
            }


        }
    }

     fun clearUsers() {
        examenes.clear()
        notifyDataSetChanged()
    }

     fun addUsuario(user: Examen) {
        this.examenes.add(user)
        notifyItemInserted(this.examenes.size - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CardUserBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val user=examenes[position]
        holder.binding.toolbarCard.title= examenes[position].titulo
        holder.binding.tema.text= examenes[position].tema
        holder.binding.detalle.text= examenes[position].detalle
        holder.binding.dificultad.text= examenes[position].dificultad
        holder.binding.idExamen.text= examenes[position].id.toString()


    }

    override fun getItemCount(): Int {
        return examenes.size
    }


}