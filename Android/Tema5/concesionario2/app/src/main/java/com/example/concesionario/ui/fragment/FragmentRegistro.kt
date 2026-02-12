package com.example.concesionario.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.concesionario.R
import com.example.concesionario.databinding.RegistroFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentRegistro: Fragment() {
private lateinit var binding: RegistroFragmentBinding
private lateinit var database: FirebaseDatabase
private lateinit var auth: FirebaseAuth
private var nombre: String?=null
private var pass: String?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
        auth= FirebaseAuth.getInstance()
        nombre=this.arguments?.getString("email")
        pass=this.arguments?.getString("pass")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=RegistroFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (nombre!=null && pass!=null){
            binding.editCorreo.setText(nombre)
            binding.editPass.setText(pass)
        }
        binding.botonRegistro.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.editCorreo.text.toString(),
                    binding.editPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful){
                    val usuarioLogeado= auth.currentUser!!.uid
                        database.reference.child("usuarios").child(usuarioLogeado).setValue(
                            binding.editCorreo.text.toString()
                        )
                val bundle= Bundle()
                bundle.putString("email",binding.editCorreo.text.toString())
                bundle.putString("pass",binding.editPass.text.toString())
                findNavController().navigate(R.id.action_fragmentRegistro_to_fragmentLogin,bundle)
                }else{
                    Snackbar.make(binding.root,"Error al registrar el usuario", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}