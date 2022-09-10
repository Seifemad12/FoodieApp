package com.example.foody.api

import com.example.foody.utils.Constants.Companion.Base_Url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ApiControll by lazy {
        retrofit.create(ApiControll::class.java)
    }


}