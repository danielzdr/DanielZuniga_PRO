package com.example.pokemon.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemon.databinding.LoginFragmentBinding

class FragmentLogin: Fragment() {
    private lateinit var binding: LoginFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LoginFragmentBinding.inflate(inflater,container,false)
        return binding.root}

    override fun onResume() {

        super.onResume()
    }
}