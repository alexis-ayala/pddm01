package com.anushka.roomdemo.network

import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://arielito.herokuapp.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ProyectoApi {

    @GET("realestate")
    fun getProperties(): Call<String>

}

object ProyApi {
    val retrofitService: ProyectoApi by lazy {
        retrofit.create(ProyectoApi::class.java)
    }

}