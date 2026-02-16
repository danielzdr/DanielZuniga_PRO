package com.example.examen.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.examen.R
import com.example.examen.databinding.LoginFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FragmentLogin: Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var auth: FirebaseAuth
    private var email: String? = null
    private var password: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()

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
        binding.btnLogin.setOnClickListener {
            email = binding.editCorreo.text.toString()
            password = binding.editPass.text.toString()
            auth.createUserWithEmailAndPassword(binding.editCorreo.text.toString()
                , binding.editPass.text.toString())
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        var bundle: Bundle = Bundle()
                        bundle.putString("email", email)
                        Snackbar.make(binding.root, "Usuario creado exitosamente", Snackbar.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMain,bundle)

                    } else {
                        Snackbar.make(binding.root, "Error, el usuario ya existe", Snackbar.LENGTH_LONG).show()
                    }
                }
        }
    }


}