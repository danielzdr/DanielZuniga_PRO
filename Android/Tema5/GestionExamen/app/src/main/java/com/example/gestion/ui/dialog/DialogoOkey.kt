package com.example.gestion.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.gestion.R

class DialogoOkey: DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Importacion correcta")
        builder.setMessage("Los datos se han importado correctamente.")
        builder.setPositiveButton("Aceptar" ){_, _ ->
                findNavController().navigate(R.id.action_dialogoOkey_to_fragmentListado)
        }
        builder.setNegativeButton("Cancelar") {_,_ ->
                findNavController().navigate(R.id.action_dialogoOkey_to_fraagmentImportacion)
        }
        return builder.create()
    }
}