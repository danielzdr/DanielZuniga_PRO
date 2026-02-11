package com.example.gestion.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogoError: DialogFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Ha ocurrido un error al cargar los datos. Por favor, inténtalo de nuevo más tarde.")
        builder.setPositiveButton("Aceptar", null)
        builder.setCancelable(false)
        return builder.create()

    }
}