package ru.zar1official.exchangerates.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zar1official.exchangerates.domain.models.CurrencyDomain
import ru.zar1official.exchangerates.domain.models.SymbolDomain

interface Repository {
    suspend fun getLatestRates(
        base: String? = null,
        symbols: List<SymbolDomain>? = null
    ): List<CurrencyDomain>

    suspend fun getAllSymbols(): List<SymbolDomain>

    fun observeFavouriteSymbols(): Flow<List<SymbolDomain>>

    suspend fun addFavourite(favourite: SymbolDomain)

    suspend fun removeFavourite(favourite: SymbolDomain)
}