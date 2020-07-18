package com.anushka.roomdemo.api

import com.anushka.roomdemo.model.Usuario
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    @FormUrlEncoded
    @POST("createuser")
    fun creatUser(
        @Field("i_usuario") id:Int,
        @Field("token") token:Int
    ): Call<Usuario>

}