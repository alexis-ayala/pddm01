package com.anushka.roomdemo.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object Retrofit {

    private const val BASE_URL = "https://arielito.herokuapp.com"

    //private val AUTH = "Basic " + Base64.encodeToString("arielchocogamerxxx:3600".toByteArray(), Base64.NO_WRAP)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorized", "")
                .method(original.method, original.body)

            val request = requestBuilder.build()
                chain.proceed(request)
        }.build()

    val instances: APIService by Lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(APIService::class.java)
    }

}

