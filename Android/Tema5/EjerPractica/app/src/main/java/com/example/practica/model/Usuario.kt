package com.example.practica.model

import java.io.Serializable

class Usuario(var nombre:String, var apellido:String, var edad: Int,
    var email: String, var password: String,var imagen: Int): Serializable {

}