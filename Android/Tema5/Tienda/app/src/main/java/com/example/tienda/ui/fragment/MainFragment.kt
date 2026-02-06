package com.example.tienda.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tienda.databinding.FragmentMainBinding
import com.example.tienda.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var uid:String
    private lateinit var auth: FirebaseAuth;
    private lateinit var database: FirebaseDatabase
    override fun onAttach(context: Context) {
        super.onAttach(context)
        uid=auth.currentUser!!.uid
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.nombreTextView.text=uid
        binding.guardarDatos.setOnClickListener {
            val referencia= database.reference.child("nombreApp").child(uid).child("usuario")
            referencia.setValue("App de tienda")
            val referencia2= database.reference.child("nombreApp").child(uid).child("usuario2")
            referencia2.setValue("Segunda linea de datos")
        }


        binding.eliminarDatos.setOnClickListener {
            val referencia= database.reference.child("nombreApp")
            referencia.setValue(null)
        }
    }
}