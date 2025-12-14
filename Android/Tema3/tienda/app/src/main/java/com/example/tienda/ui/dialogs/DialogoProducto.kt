package com.example.tienda.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.tienda.adapter.AdapterCarrito
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.dataset.DataSet
import com.example.tienda.ui.activitys.ProductosActivity

class DialogoProducto: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builderProducto= AlertDialog.Builder(requireContext())


        return builderProducto.create()
    }
}