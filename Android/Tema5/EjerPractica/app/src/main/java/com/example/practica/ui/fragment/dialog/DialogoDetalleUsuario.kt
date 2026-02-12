package com.example.practica.ui.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.practica.databinding.DialogoDetalleUsuarioBinding
import com.example.practica.model.Usuario

class DialogoDetalleUsuario: DialogFragment() {
    private lateinit var binding: DialogoDetalleUsuarioBinding
    private lateinit var usuario: Usuario
    companion object{

        fun newInstance(user: Usuario): DialogoDetalleUsuario{
            val dialogo=DialogoDetalleUsuario()
            val bundle= Bundle()
            bundle.putString("usuario",user.toString())
            dialogo.arguments=bundle
            return dialogo
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.usuario=this.arguments?.getSerializable("usuario") as Usuario
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        binding= DialogoDetalleUsuarioBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        binding.txtNombres.text=usuario.nombre
        binding.txtApellido.text=usuario.apellido
        binding.txtEmail.text=usuario.email
        binding.textPass.text=usuario.password
        binding.txtEdad.text=usuario.edad.toString()

        //Glide.with(requireContext())
           // .load(usuario.imagen) .into(binding.imgUsuario)
        return builder.create()
    }


}