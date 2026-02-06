package com.example.tienda.ui.fragment

import android.content.Context
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import com.example.tienda.adapter.AdapterUser
import com.example.tienda.databinding.RegistroFragmentBinding
import com.example.tienda.dataset.DatasetUser
import com.example.tienda.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class RegistroFragment: Fragment() {

    private lateinit var auth: FirebaseAuth;
    private lateinit var binding: RegistroFragmentBinding
    private lateinit var adapterEdad: ArrayAdapter<Int>
    private lateinit var listaEdades: ArrayList<Int>

    private var nombre: String? = null
    private var pass: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
        listaEdades = ArrayList()
        for (i in 18..90) {
            listaEdades.add(i)
        }
        adapterEdad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listaEdades
        )
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nombre = this.arguments?.getString("email")
        pass = this.arguments?.getString("pass")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistroFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (nombre != null || pass != null) {
            binding.textoMail.setText(nombre)
            binding.textoPass.setText(pass)
        }
        binding.spinnerEdad.adapter = adapterEdad
        binding.botonReg.setOnClickListener {
            /*
            if (DatasetUser.registerUser(
                    Usuario(
                        binding.textNombre.text.toString(),
                        binding.textoMail.text.toString(),
                        binding.spinnerEdad.selectedItem.toString().toInt(),
                        binding.textoMail.text.toString(),
                        binding.textoPass.text.toString())
                )){
                findNavController().navigate(R.id.action_registroFragment_to_mainFragment)
                Snackbar.make(binding.root,
                    "Usuario registrado con exito", Snackbar.LENGTH_LONG)
                    .show()
            }else{
                Snackbar.make(binding.root,
                    "El usuario ya existe", Snackbar.LENGTH_LONG)
                    .show()
            }
        } */
            auth.createUserWithEmailAndPassword(
                binding.textoMail.text.toString(),
                binding.textoPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful){
                    Snackbar.make(binding.root,"Usuario registrado con exito", Snackbar.LENGTH_SHORT)
                        .show()
                    val usuarioLogeado=auth.currentUser!!.uid
                    val bundle= Bundle()
                    bundle.putString("uid",usuarioLogeado)
                    findNavController().navigate(R.id.action_registroFragment_to_dialogoRegistro)
                }else{
                    findNavController().navigate(R.id.action_registroFragment_to_dialogoError)
                }
            }

        }
    }
}