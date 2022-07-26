package ru.zar1official.exchangerates.data.network.models

import com.google.gson.annotations.SerializedName

data class SymbolsResponse(@SerializedName("symbols") val symbols: Map<String, SymbolInfo>) {
    data class SymbolInfo(
        @SerializedName("description") val description: String,
        @SerializedName("code") val code: String
    )
}