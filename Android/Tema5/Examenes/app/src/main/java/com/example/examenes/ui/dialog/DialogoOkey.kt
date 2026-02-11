package com.example.examenes.ui.dialog

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.examenes.R

class DialogoOkey: DialogFragment() {
    val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())

     override fun onCreateDialog(savedInstanceState: android.os.Bundle?): android.app.Dialog {
        builder.setTitle("Registro correcto")
        builder.setMessage("El usuario se ha registrado correctamente.")
        builder.setPositiveButton("OK") { _, _ ->
            // Acción a realizar al hacer clic en "OK"
            findNavController().navigate(R.id.action_dialogoOkey_to_fragmentListado)
        }
         builder.setNegativeButton("Cancelar") { _, _ ->
             // Acción a realizar al hacer clic en "Cancelar"
             findNavController().navigate(R.id.action_dialogoOkey_to_fragmentImportacion)
         }
        return builder.create()
    }
}