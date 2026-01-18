package com.example.agendajson.dialogos

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogoFiltrar : DialogFragment() {

    private var seleccion = 2

    interface OnGeneroSeleccionadoListener {
        fun onGeneroSeleccionado(genero: String)
    }

    private var listener: OnGeneroSeleccionadoListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OnGeneroSeleccionadoListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context debe implementar OnGeneroSeleccionadoListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val opciones = arrayOf("Masculino", "Femenino", "Todos")

        return AlertDialog.Builder(requireContext())
            .setTitle("Filtrar por género")
            .setSingleChoiceItems(opciones, seleccion) { _, which ->
                seleccion = which
            }
            .setPositiveButton("Filtrar") { _, _ ->
                val generoSeleccionado = when (seleccion) {
                    0 -> "male"
                    1 -> "female"
                    2 -> "Todos"
                    else -> "Todos"
                }
                listener?.onGeneroSeleccionado(generoSeleccionado)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}