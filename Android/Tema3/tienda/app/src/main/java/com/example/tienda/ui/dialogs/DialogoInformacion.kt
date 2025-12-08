package com.example.tienda.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment

class DialogoInformacion: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builder.setTitle("Información")
        builder.setMessage("Esta es una tienda de ejemplo creada para demostrar el uso de RecyclerView")
        //sustituir por guion bajo cuando ya me dice quien es
        builder.setPositiveButton("OK", {_,_-> {
            Log.v("dialogo","Pulsado OK")
        } })
        builder.setNegativeButton("NO", {_,_-> {
            Log.v("dialogo","Pulsado NO")
        } })
        builder.setNeutralButton("CANCEL", {_,_-> {
            Log.v("dialogo","Pulsado CANCEL")
        } })


        //Configura el dialogo para que salga en pantalla
        return builder.create()
    }

}