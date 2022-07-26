package ru.zar1official.exchangerates.util

import ru.zar1official.exchangerates.data.local.models.FavouriteCurrency
import ru.zar1official.exchangerates.data.network.models.LatestResponse
import ru.zar1official.exchangerates.data.network.models.SymbolsResponse
import ru.zar1official.exchangerates.domain.models.CurrencyDomain
import ru.zar1official.exchangerates.domain.models.SymbolDomain

fun LatestResponse.toCurrencyDomainList(): List<CurrencyDomain> {
    return buildList {
        for (entry in this@toCurrencyDomainList.rates) {
            val domain = CurrencyDomain(
                name = entry.key,
                rate = entry.value
            )
            add(domain)
        }
    }
}

fun SymbolsResponse.toSymbolDomainList(): List<SymbolDomain> {
    return buildList {
        val names = this@toSymbolDomainList.symbols.keys
        names.forEach {
            add(SymbolDomain(it))
        }
    }
}

fun FavouriteCurrency.toSymbolDomain(): SymbolDomain {
    return SymbolDomain(this.name)
}

fun List<FavouriteCurrency>.toSymbolDomainList(): List<SymbolDomain> {
    return this.map { it.toSymbolDomain() }
}