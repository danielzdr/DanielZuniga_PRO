package com.example.tienda.model

import java.io.Serializable

class Usuario(var nombre: String, var apellido:String, var edad: Int,var email: String, var password: String):
    Serializable {
}