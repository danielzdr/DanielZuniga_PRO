package com.example.tienda.dataset

import android.widget.Toast
import com.example.tienda.model.Usuario
import com.google.android.material.snackbar.Snackbar

class DatasetUser {
    companion object{
        val listaUsuarios= arrayListOf<Usuario>()

        fun registerUser(user: Usuario): Boolean {
            if (listaUsuarios.find { it.email == user.email } != null) {
                return false
            } else {
                this.listaUsuarios.add(user)
                return true
            }
        }

        fun loginUser(correo: String, pass: String): Boolean {
            return listaUsuarios
                .find { it.email == correo && it.password == pass } != null;
        }

    }
}