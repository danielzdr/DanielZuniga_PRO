package com.example.futbol.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.futbol.databinding.RegistroFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class FragmentRegistro: Fragment() {
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth

    lateinit var binding: RegistroFragmentBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= RegistroFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()


    }
}