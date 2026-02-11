package com.example.futbol.dataset

class DataSet {
    companion object{
        val listaLigas = ArrayList<String>()
            fun añadirFavoritos(liga: String){
                listaLigas.add(liga)
            }

            fun eliminarFavoritos(liga: String){
                listaLigas.remove(liga)
            }

            fun mostrarFavoritos(): ArrayList<String>{
                return listaLigas
            }


    }
}