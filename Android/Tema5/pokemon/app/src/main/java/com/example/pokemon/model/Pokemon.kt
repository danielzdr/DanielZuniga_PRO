package com.example.pokemon.model

import java.io.Serializable

 class Pokemon (
    val pokedexID: Long? = null,
    val name: String? = null,
    val generation: Long? = null,
    val category: String? = null,
    val height: String? = null,
    val weight: String? = null,

): Serializable{

}