package com.example.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/positions")
    fun sendPosition(@Body position: Position): Call<String>
}
