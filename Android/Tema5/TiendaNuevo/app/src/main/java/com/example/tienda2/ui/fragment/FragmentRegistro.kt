package com.example.tienda2.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tienda2.R
import com.example.tienda2.databinding.RegistroFragmentBinding
import com.example.tienda2.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentRegistro : Fragment() {
    private lateinit var binding: RegistroFragmentBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var listaEdades: ArrayList<Int>
    private lateinit var AdapterEdad: ArrayAdapter<Int>
    private lateinit var auth: FirebaseAuth
    private var nombre: String? = null
    private var pass: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listaEdades = ArrayList()
        for (i in 18..90) {
            listaEdades.add(i)
        }
        AdapterEdad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listaEdades
        )
        AdapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_item)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        nombre = this.arguments?.getString("nombre")
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
        if (nombre != null && pass != null) {
            binding.editCorreo.setText(nombre)
            binding.editPass.setText(pass)
        }
        binding.spinnerEdad.adapter = AdapterEdad
        binding.botonRegistro.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.editCorreo.text.toString(),
                binding.editPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.root, "Usuario registrado", Snackbar.LENGTH_SHORT).show()
                    val usuarioLogeado = auth.currentUser!!.uid
                    database.reference.child("usuarios").child(usuarioLogeado)
                        .setValue(
                            Usuario(
                                binding.editNombre.text.toString(),
                                binding.editApellido.text.toString(),
                                binding.spinnerEdad.selectedItem.toString().toInt(),
                                binding.editCorreo.text.toString(),
                                binding.editPass.text.toString()
                            )
                        )
                    val bundle = Bundle()
                    bundle.putString("nombre", binding.editCorreo.text.toString())
                    bundle.putString("pass", binding.editPass.text.toString())
                    findNavController().navigate(R.id.action_fragmentRegistro_to_fragmentLogin, bundle) }
                else {
                    Snackbar.make(binding.root, "Error al registrar usuario", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        super.onResume()
    }
}
