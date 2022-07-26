package ru.zar1official.exchangerates.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zar1official.exchangerates.data.local.RoomDao
import ru.zar1official.exchangerates.data.network.CurrencyService
import ru.zar1official.exchangerates.domain.models.CurrencyDomain
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.domain.repository.Repository
import ru.zar1official.exchangerates.util.convertToString
import ru.zar1official.exchangerates.util.toCurrencyDomainList
import ru.zar1official.exchangerates.util.toFavouriteCurrency
import ru.zar1official.exchangerates.util.toSymbolDomainList
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: CurrencyService,
    private val roomDao: RoomDao
) : Repository {
    override suspend fun getLatestRates(
        base: String?,
        symbols: List<SymbolDomain>?
    ): List<CurrencyDomain> {
        val response = service.getLatestResponse(
            symbols = symbols?.convertToString(),
            base = base
        )
        return response.toCurrencyDomainList()
    }

    override suspend fun getAllSymbols(): List<SymbolDomain> {
        return service.getSymbolsResponse().toSymbolDomainList()
    }

    override fun observeFavouriteSymbols(): Flow<List<SymbolDomain>> {
        return roomDao.getFavourites().map { it.toSymbolDomainList() }
    }

    override suspend fun addFavourite(favourite: SymbolDomain) {
        roomDao.insertFavourite(favourite.toFavouriteCurrency())
    }

    override suspend fun removeFavourite(favourite: SymbolDomain) {
        roomDao.deleteFavourite(favourite = favourite.toFavouriteCurrency())
    }
}

