package com.example.examenes.adapter

import android.content.Context
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.examenes.R
import com.example.examenes.databinding.CardExamenBinding
import com.example.examenes.model.Examen
import com.google.android.material.snackbar.Snackbar

class AdapterExamen(var context: Context): RecyclerView.Adapter<AdapterExamen.MyHolder>() {
    private var examenes: MutableList<Examen> = mutableListOf()

    inner class MyHolder(var binding: CardExamenBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.toolbarCard.inflateMenu(R.menu.menu_card)
                binding.toolbarCard.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_ver_detalles -> {
                            val bundle :Bundle= Bundle()
                            bundle.putSerializable("examen", examenes[adapterPosition])
                            findNavController(binding.root).navigate(R.id.action_fragmentListado_to_fragmentDetalle,bundle)

                        }
                        R.id.menu_dificultad -> {
                            Snackbar.make(binding.root, "Dificultad: ${examenes[adapterPosition].dificultad}", Snackbar.LENGTH_SHORT).show()
                        }

                    }
                    return@setOnMenuItemClickListener true
                }
        }
    }

    fun clearExamenes() {
        examenes.clear()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): AdapterExamen.MyHolder {
        val binding = CardExamenBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterExamen.MyHolder, position: Int) {
        val examen = examenes[position]
        holder.binding.toolbarCard.title = examen.titulo
        holder.binding.tema.text= examen.tema
        holder.binding.detalle.text= examen.detalle
        holder.binding.dificultad.text=examen.dificultad
        holder.binding.idExamen.text=examen.id.toString()
    }

    override fun getItemCount(): Int {
        return examenes.size
    }
}