package com.route.islamic39.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://mp3quran.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServices(): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}
