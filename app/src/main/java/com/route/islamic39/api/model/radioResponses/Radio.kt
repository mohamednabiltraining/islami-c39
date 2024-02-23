package com.route.islamic39.api.model.radioResponses

import com.google.gson.annotations.SerializedName

data class Radio(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)
