package com.example.tienda.ui.dialogs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tienda.ui.activitys.ProductosActivity
import com.example.tienda.ui.activitys.SecondActivity

class DialogoDetalle: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Detalle del producto")
        builder.setMessage("Aquí se mostrarán los detalles del producto seleccionado.")
        builder.setPositiveButton("OK", {_,_-> {
            // Acción al pulsar OK
            Log.v("dialogo","Pulsado OK")


        } })
        builder.setNegativeButton("CANCEL", {_,_-> {
            // Acción al pulsar CANCEL
            Log.v("dialogo","Pulsado CANCEL")
        } })
        builder.setNeutralButton("CLOSE", {_,_-> {
            // Acción al pulsar CLOSE
            Log.v("dialogo","Pulsado CLOSE")
        } })
        //Configura el dialogo para que salga en pantalla
        return builder.create()
    }
}