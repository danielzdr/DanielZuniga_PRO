package com.example.agendajson.dialogos
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.agendajson.databinding.DialogDetallesUsuarioBinding
import com.example.agendajson.model.User

class DialogoDetallesUsuario: DialogFragment() {
    private lateinit var binding: DialogDetallesUsuarioBinding
    private lateinit var usuario: User

    //para crear una instancia del diálogo con el usuario como argumento
    companion object {
        fun newInstance(usuario: User): DialogoDetallesUsuario {
            val dialogo = DialogoDetallesUsuario()
            val bundle = Bundle()
            bundle.putSerializable("usuario", usuario)
            dialogo.arguments = bundle
            return dialogo
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Obtener el usuario de los argumentos
        this.usuario = this.arguments?.getSerializable("usuario") as User
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder= AlertDialog.Builder(requireContext())
        binding = DialogDetallesUsuarioBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        // Configurar la vista del diálogo con los detalles del usuario
        binding.txtEmail.text=usuario.email
        binding.txtNombres.text=usuario.firstName
        binding.txtGenero.text=usuario.gender.toString()
        binding.txtEdad.text=usuario.age.toString()

        // Cargar la imagen del usuario con Glide
        Glide.with(requireContext())
            .load(usuario.image)
            .into(binding.imgUsuario)
        return builder.create()
    }


}