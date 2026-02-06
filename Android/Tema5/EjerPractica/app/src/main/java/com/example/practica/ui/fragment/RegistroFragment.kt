package com.example.practica.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practica.R
import com.example.practica.databinding.FragmentRegistroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegistroFragment: Fragment() {
    private lateinit var binding: FragmentRegistroBinding
    private lateinit var auth: FirebaseAuth
    private val urlBase = "https://dummyjson.com/users"
    private lateinit var listaEdades: ArrayList<Int>
    private lateinit var AdapterEdad: ArrayAdapter<Int>
    private var nombre: String? = null
    private var pass: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
        listaEdades = ArrayList()
        for (i in 18..90) {
            listaEdades.add(i)
        }
        AdapterEdad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listaEdades
        )
        AdapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nombre = this.arguments?.getString("email")
        pass = this.arguments?.getString("pass")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegistroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (nombre != null || pass != null) {
            binding.textoMail.setText(nombre)
            binding.textoPass.setText(pass)
        }
        binding.spinnerEdad.adapter = AdapterEdad
        binding.botonReg.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.textoMail.text.toString(),
                binding.textoPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.root, "Usuario registrado exitosamente", Snackbar.LENGTH_LONG).show()
                    val usuarioLogeado = auth.currentUser!!.uid
                    val bundle= Bundle()
                    bundle.putString("id",usuarioLogeado)
                    findNavController().navigate(R.id.action_registroFragment_to_dialogoOk)
                } else {
                    // Ocurrió un error durante el registro, maneja el error aquí
                    findNavController().navigate(R.id.action_registroFragment_to_dialogoError)
                }
            }

        }
    }
}