package com.example.examenes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.examenes.databinding.DetalleFragmentBinding

class FragmentDetalle: Fragment() {
    private lateinit var binding: DetalleFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val examen= arguments?.getSerializable("examen")
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
        binding.textTitulo.text= arguments?.getString("titulo")
        binding.textTema.text= arguments?.getString("tema")
        binding.textDetalle.text= arguments?.getString("detalle")
        binding.textDificultad.text=arguments?.getString("dificultad")
        binding.textId.text= arguments?.getString("id").toString()
    }


}