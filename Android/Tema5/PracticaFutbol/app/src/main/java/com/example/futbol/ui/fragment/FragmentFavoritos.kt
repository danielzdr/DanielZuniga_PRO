package com.example.futbol.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.futbol.databinding.FavoritosFragmentBinding
import com.example.futbol.dataset.DataSet

class FragmentFavoritos: Fragment() {
    lateinit var binding: FavoritosFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FavoritosFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        DataSet.mostrarFavoritos()
    }
}