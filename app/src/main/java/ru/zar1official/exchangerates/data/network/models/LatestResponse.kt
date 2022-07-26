package ru.zar1official.exchangerates.data.network.models

import com.google.gson.annotations.SerializedName

data class LatestResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: Map<String, Float>
)