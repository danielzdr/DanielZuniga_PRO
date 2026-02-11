package com.example.examenes.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.examenes.R


class DialogoError: DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Error de importacion")
        builder.setMessage("Error al importar los examenes, por favor intente de nuevo.")
        builder.setPositiveButton("OK") { _, _ ->
            findNavController().navigate(R.id.action_dialogoError_to_fragmentImportacion)
        }
        return builder.create()
    }
}