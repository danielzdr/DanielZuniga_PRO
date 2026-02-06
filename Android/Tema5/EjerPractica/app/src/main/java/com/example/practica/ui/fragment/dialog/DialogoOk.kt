package com.example.practica.ui.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.practica.R

class DialogoOk: DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Registro correcto")
        builder.setMessage("El usuario se ha registrado correctamente.")
        builder.setPositiveButton("OK"){dialog, which ->
            findNavController().navigate(R.id.action_dialogoOk_to_mainFragment)
        }
        builder.setNegativeButton("Cancelar"){dialog, which ->
            findNavController().navigate(R.id.action_dialogoOk_to_loginFragment2)
        }
        return builder.create()
    }
}