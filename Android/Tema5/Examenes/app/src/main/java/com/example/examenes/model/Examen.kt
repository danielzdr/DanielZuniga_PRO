package com.example.examenes.model

import java.io.Serializable

class Examen(var titulo:String, var tema:String, var detalle: String,
             var dificultad: String, var id: Int): Serializable {
}