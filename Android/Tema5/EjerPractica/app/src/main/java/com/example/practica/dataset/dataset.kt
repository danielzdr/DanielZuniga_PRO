package com.example.practica.dataset

import com.example.practica.model.Usuario

class dataset {
    companion object {
        val listaUsuarios = arrayListOf<Usuario>()

        fun registrarUsuario(user: Usuario): Boolean {
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