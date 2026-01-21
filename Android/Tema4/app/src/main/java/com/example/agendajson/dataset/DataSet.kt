package com.example.agendajson.dataset

import android.util.Log
import com.example.agendajson.model.User

class DataSet {
    companion object{
        val listaUsuarios= arrayListOf<User>()


        fun agregarUsersFavs(x: User): Unit {
            listaUsuarios.add(x)
            Log.v("datos","agregado")
        }
    }
}