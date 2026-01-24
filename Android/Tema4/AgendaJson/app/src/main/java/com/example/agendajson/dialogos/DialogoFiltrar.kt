package com.example.agendajson.dialogos

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.agendajson.MainActivity

class DialogoFiltrar : DialogFragment() {

    private lateinit var opciones: Array<CharSequence>
    private lateinit var listener: OnGeneroSeleccionadoListener
    private var posicion: Int = -1

    //primer metodo del ciclo de vida del diálogo que se llama cuando el diálogo se adjunta al contexto
    override fun onAttach(context: Context) {
        super.onAttach(context)
        opciones = arrayOf("Masculino", "Femenino", "Todos")
        listener = context as MainActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Filtrar")
        builder.setSingleChoiceItems(
            opciones, -1,
            { _, p ->
                posicion = p
            })
        builder.setPositiveButton("OK", { v, p ->
            listener.onGeneroSeleccionado(opciones[posicion].toString().toLowerCase())
        })

        return builder.create()
    }

    interface OnGeneroSeleccionadoListener {
        fun onGeneroSeleccionado(genero: String)
    }
}