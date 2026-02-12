package com.example.concesionario.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.concesionario.R

class DialogoRegistro: DialogFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //quiero coger los argumentos que me han pasado del fragment login
        arguments?.getString("email")
        arguments?.getString("pass")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder=AlertDialog.Builder(requireContext())
        builder.setTitle("Registro")
        builder.setMessage("Quieres registrarte como admin o cliente")
        builder.setPositiveButton("Admin"){_,_->
            (requireActivity() as DialogoRegistroListener).onDialogoRegistroSelected("admin")
            findNavController().navigate(R.id.action_dialogoRegistro_to_fragmentRegistro)
        }
        builder.setNegativeButton("Cliente"){_,_->
            (requireActivity() as DialogoRegistroListener).onDialogoRegistroSelected("cliente") }
        findNavController().navigate(R.id.action_dialogoRegistro_to_fragmentRegistro)
        return builder.create()
    }

    interface DialogoRegistroListener{
        fun onDialogoRegistroSelected(tipo: String)
    }
}