package com.example.gestion.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestion.databinding.DetalleFragmentBinding

class FragmentDetalle: Fragment() {

    private lateinit var binding: DetalleFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val examen = arguments?.getSerializable("examen")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DetalleFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        binding.textTitulo.text= "Titulo: ${arguments?.getSerializable("examen")}"
        binding.textTema.text= "Tema: ${arguments?.getSerializable("examen")}"
        binding.textDetalle.text= "Detalle: ${arguments?.getSerializable("examen")}"
        binding.textDificultad.text= "Dificultad: ${arguments?.getSerializable("examen")}"
        binding.textId.text= "ID: ${arguments?.getSerializable("examen").toString()}"



    }
}