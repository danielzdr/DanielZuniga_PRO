package com.example.tienda.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import com.google.firebase.auth.FirebaseAuth

class DialogoRegistro: DialogFragment() {
    private lateinit var uid: String
    private lateinit var auth: FirebaseAuth
    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
        uid=auth.currentUser!!.uid
        //para capturar los argumentos enviados al dialogo
        //uid=requireArguments().getString("uid").toString()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Registro correcto")
        builder.setMessage("¿Quieres iniciar sesion de usuario $uid?")
        builder.setPositiveButton("SI"){dialog,which->
            findNavController().navigate(R.id.action_dialogoRegistro_to_mainFragment)
        }
        builder.setNegativeButton("NO"){dialog, which ->
            auth.signOut()
            findNavController().navigate(R.id.action_dialogoRegistro_to_loginFragment)
        }
        return builder.create()
    }
}