package com.example.concesionario.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.concesionario.R
import com.example.concesionario.databinding.LoginFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FragmentLogin: Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var auth: FirebaseAuth
    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.botonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.textMail.text.toString(),
                binding.textPass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_fragmentLogin_to_fragmentListado)
                } else {
                    Snackbar.make(
                        binding.root, "Usuario o contraseña incorrectos",
                        Snackbar.LENGTH_SHORT
                    ).setAction("Quieres registrarte?") {
                        val bundle= Bundle()
                        bundle.putString("email",binding.textMail.text.toString())
                        bundle.putString("pass",binding.textPass.text.toString())
                        findNavController().navigate(R.id.action_fragmentLogin_to_dialogoRegistro,bundle)
                    }.show()
                }

            }
            binding.botonRegistro.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentLogin_to_dialogoRegistro)
            }
        }
    }
}