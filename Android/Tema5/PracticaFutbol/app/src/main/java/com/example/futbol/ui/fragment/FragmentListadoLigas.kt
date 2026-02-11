package com.example.futbol.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.futbol.databinding.ListadoFragmentBinding

class FragmentListadoLigas: Fragment() {

    private lateinit var adapterLigas: ArrayAdapter<Int>
    private lateinit var listaLigas: ArrayList<Int>

    lateinit var binding: ListadoFragmentBinding

    override fun onAttach(context: Context) {
        listaLigas = ArrayList()
        for (i in 1..5) {
            listaLigas.add(i)
            super.onAttach(context)
        }
        adapterLigas= ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listaLigas
        )
        adapterLigas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = ListadoFragmentBinding.inflate(inflater, container, false)
            binding.spinnerLigas.adapter=adapterLigas
            return binding.root
        }

        override fun onResume() {
            super.onResume()
        }

}