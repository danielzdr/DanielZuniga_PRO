package com.example.tienda.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import com.example.tienda.databinding.LoginFragmentBinding
import com.example.tienda.dataset.DatasetUser
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment: Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var auth: FirebaseAuth;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LoginFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.botonIniciarSesion.setOnClickListener {
            if (DatasetUser.loginUser(binding.textoMail.text.toString() ,
                    binding.textoPass.text.toString())){
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }else{
                Snackbar.make(binding.root,
                    "Usuario o contraseña incorrectos", Snackbar.LENGTH_LONG)
                    .setAction("Quieres registrarte?"){
                        //para pasar datos entre fragments
                        val bundle: Bundle = Bundle()
                        bundle.putString("email",binding.textoMail.text.toString())
                        bundle.putString("pass",binding.textoPass.text.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_registroFragment,
                            bundle)
                    }
                    .show()
            }


        }
        binding.botonRegistrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

    }
}