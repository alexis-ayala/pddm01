package com.anushka.roomdemo.api

import com.anushka.roomdemo.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun userLogin(@Body userInfo: Usuario): Call<Usuario>

    @Headers("Content-Type: application/json")
    @POST("registrar")
    fun registerUser (@Body userInfo: Usuario): Call<Usuario>

}