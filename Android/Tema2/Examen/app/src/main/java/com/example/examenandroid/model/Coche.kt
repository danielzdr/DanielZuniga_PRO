package com.example.examenandroid.model

class Coche(var matricula: String, var anioMatriculacion:Int, var tipoGasolina:String,
    var autonomia: Int, var imagen:Int){

    override fun toString(): String {
        return "Coche(matricula='$matricula', anioMatriculacion=$anioMatriculacion, tipoGasolina='$tipoGasolina', autonomia=$autonomia)"
    }

    fun comprobarTipodeGasolina(): String{
        return when(tipoGasolina){
            "Gasolina"->"Gasolina"
            "Diesel"->"Diesel"
            "Hibrido"->"Hibrido"
            "Electrico"->"Electrico"
            else -> "Tipo de gasolina no valido"
        }
    }
    }
