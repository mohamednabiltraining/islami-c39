package com.route.islamic39.api.model.radioResponses

import com.google.gson.annotations.SerializedName

data class RadioResources(
    @SerializedName("radios")
    val radios: List<Radio>? = listOf()
)
