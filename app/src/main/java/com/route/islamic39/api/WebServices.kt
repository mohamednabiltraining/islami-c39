package com.route.islamic39.api

import com.route.islamic39.api.model.radioResponses.RadioResources
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {
    @GET("api/v3/radios")
    fun getRadioResources(): Call<RadioResources>
}
