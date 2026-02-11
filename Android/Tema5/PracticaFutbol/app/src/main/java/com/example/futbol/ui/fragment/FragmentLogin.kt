package com.example.futbol.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.futbol.R
import com.example.futbol.databinding.LoginFragmentBinding
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentLogin: Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var binding: LoginFragmentBinding
    private var email: String? = null
    private var pass: String? = null

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
        binding.loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passEditText.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_fragmentLogin_to_fragmentPerfil)
                    //navegar al main
                } else {
                    //mostrar error
                    Snackbar.make(binding.root,
                        "Usuario o contraseña incorrectos", Snackbar.LENGTH_LONG)
                        .setAction("Quieres registrarte?"){
                            //para pasar datos entre fragments
                            val bundle: Bundle = Bundle()
                            bundle.putString("email",binding.emailEditText.text.toString())
                            bundle.putString("pass",binding.passEditText.text.toString())
                            findNavController().navigate(
                                R.id.action_fragmentLogin_to_fragmentRegistro, bundle)
                        }
                        .show()
                }
            }
        }

    }


}