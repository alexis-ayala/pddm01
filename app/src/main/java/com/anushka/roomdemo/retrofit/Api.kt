package com.anushka.roomdemo.retrofit

import com.anushka.roomdemo.model.Usuario
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("usuario") user:String,
        @Field("contra") pass:String
    ): Call<Usuario>


    @POST("registrar")
    fun createUser(
        @Field("usuario") user:String,
        @Field("contra") pass: String
    ): Call<Usuario>

}