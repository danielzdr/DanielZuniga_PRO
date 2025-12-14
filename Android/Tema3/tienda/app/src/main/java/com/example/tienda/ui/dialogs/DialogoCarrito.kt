package com.example.tienda.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.tienda.ui.activitys.ProductosActivity

class DialogoCarrito: DialogFragment() {
    private lateinit var listener: OnDialogoCarritoListener
    override fun onAttach(context: Context) {
        listener= context as OnDialogoCarritoListener
        super.onAttach(context)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builderCarrito: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        builderCarrito.setTitle("Carrito")
        builderCarrito.setMessage("El producto ha sido añadido al carrito, ¿seguro que quieres pasar al carrito?")
        builderCarrito.setPositiveButton("SI", {_,_-> {
            Log.v("dialogo","Pulsado SI")
            listener.onConfirmarSelected()
        } })
        builderCarrito.setNegativeButton("NO", {_,_-> {
            Log.v("dialogo","Pulsado NO")
        } })
        builderCarrito.setNeutralButton("CANCEL", {_,_-> {
            Log.v("dialogo","Pulsado CANCEL")
        } })

        //Configura el dialogo del carrito para que salga en pantalla
        return builderCarrito.create()

    }

    interface OnDialogoCarritoListener {
        fun onConfirmarSelected()
    }
}