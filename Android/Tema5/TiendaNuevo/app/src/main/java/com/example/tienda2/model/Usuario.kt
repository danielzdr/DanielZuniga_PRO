package com.example.tienda2.model

import java.io.Serializable

class Usuario(var nombre: String?=null, var apellido: String?=null, var edad: Int?=0, var email:
String?=null,var password:String?=null, var id:Int?=0) : Serializable{
}