package com.example.agendajson.dialogos

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.DialogDetallesUsuarioBinding
import com.example.agendajson.model.User

class DialogoDetallesUsuario: DialogFragment() {
    private lateinit var binding: DialogDetallesUsuarioBinding
    private lateinit var usuario: User

    companion object {
        fun newInstance(usuario: User): DialogoDetallesUsuario {
            return DialogoDetallesUsuario().apply {
                arguments = Bundle().apply {
                    putSerializable("usuario", usuario)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtener el usuario de los argumentos
        arguments?.let {
            usuario = it.getSerializable("usuario") as User
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            // Inflar el layout
            binding = DialogDetallesUsuarioBinding.inflate(layoutInflater)

            // Configurar los datos del usuario
            configurarDatosUsuario()

            // Configurar botones
            configurarBotones()

            // Crear el diálogo sin título predeterminado
            val dialog = AlertDialog.Builder(activity)
                .setView(binding.root)
                .create()

            // Personalizar el título del diálogo
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

            dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun configurarDatosUsuario() {
        // Nombre completo (con maidenName si existe)
        val nombreCompleto = buildString {
            usuario.firstName?.let { append(it) }
            usuario.maidenName?.let { append(" $it") }
            usuario.lastName?.let { append(" $it") }
        }
        binding.txtNombreCompleto.text = nombreCompleto

        // Email
        binding.txtEmail.text = usuario.email ?: "Email no disponible"

        // ID
        binding.txtId.text = usuario.id?.toString() ?: "N/A"

        // Género (traducir)
        val generoTexto = when (usuario.gender?.toLowerCase()) {
            "male" -> "Masculino"
            "female" -> "Femenino"
            else -> usuario.gender ?: "No especificado"
        }
        binding.txtGenero.text = generoTexto

        // Edad
        val edadTexto = if (usuario.age != null) {
            "${usuario.age} años"
        } else {
            "Edad no disponible"
        }
        binding.txtEdad.text = edadTexto

        // Nombres desglosados
        val nombresDesglosados = buildString {
            append("Primer nombre: ${usuario.firstName ?: "N/A"}\n")
            usuario.maidenName?.let { append("Segundo nombre: $it\n") }
            append("Apellido: ${usuario.lastName ?: "N/A"}")
        }
        binding.txtNombres.text = nombresDesglosados

        // Cargar imagen si existe
        cargarImagenUsuario()
    }

    private fun cargarImagenUsuario() {
        usuario.image?.let { urlImagen ->
            if (urlImagen.isNotBlank()) {
                try {
                    // Con Glide
                    Glide.with(requireContext())
                        .load(urlImagen)
                        .into(binding.imgUsuario)
                } catch (e: Exception) {
                    // Fallback a Volley si no tienes Glide
                    cargarImagenConVolley(urlImagen)
                }
            } else {
                binding.imgUsuario.setImageResource(R.drawable.ic_launcher_background)
            }
        } ?: run {
            binding.imgUsuario.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    private fun cargarImagenConVolley(urlImagen: String) {
        val imageRequest = ImageRequest(
            urlImagen,
            { bitmap ->
                // Redondear la imagen
                val roundedBitmap = redondearImagen(bitmap)
                binding.imgUsuario.setImageBitmap(roundedBitmap)
            },
            0, // Ancho máximo
            0, // Alto máximo
            ImageView.ScaleType.CENTER_CROP,
            null,
            { error ->
                Log.e("IMAGE_LOAD", "Error cargando imagen: ${error.message}")
                binding.imgUsuario.setImageResource(R.drawable.ic_launcher_background)
            }
        )

        Volley.newRequestQueue(requireContext()).add(imageRequest)
    }

    private fun redondearImagen(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
        }

        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val radius = bitmap.width / 2f

        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(radius, radius, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }

    private fun configurarBotones() {
        // Botón cerrar
        binding.btnCerrar.setOnClickListener {
            dismiss()
        }

        // Botón contactar (solo si hay email)
        if (usuario.email != null && usuario.email!!.isNotBlank()) {
            binding.btnContactar.setOnClickListener {
                abrirEmail(usuario.email!!)
            }
        } else {
            // Ocultar botón si no hay email
            binding.btnContactar.visibility = View.GONE
        }
    }

    private fun abrirEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, "Contacto desde la app")
            putExtra(Intent.EXTRA_TEXT, "Hola ${usuario.firstName},\n\nMe gustaría contactar contigo.")
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar email mediante:"))
        } else {
            Toast.makeText(
                requireContext(),
                "No hay aplicación de email instalada",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        // Ajustar tamaño del diálogo
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    }
}