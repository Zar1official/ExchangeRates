package ru.zar1official.exchangerates.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.zar1official.exchangerates.data.network.models.LatestResponse
import ru.zar1official.exchangerates.data.network.models.SymbolsResponse

interface CurrencyService {
    @GET("symbols")
    suspend fun getSymbolsResponse(): SymbolsResponse

    @GET("latest")
    suspend fun getLatestResponse(
        @Query("symbols") symbols: String? = null,
        @Query("base") base: String? = null
    ): LatestResponse
}