package ru.zar1official.exchangerates.util

import ru.zar1official.exchangerates.domain.models.CurrencyDomain
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.presentation.models.CurrencyModel
import ru.zar1official.exchangerates.presentation.models.SymbolModel

fun CurrencyDomain.toCurrencyModel(isFavourite: Boolean = false): CurrencyModel {
    return CurrencyModel(
        name = this.name,
        rate = this.rate,
        isFavourite = isFavourite
    )
}

fun List<CurrencyDomain>.toCurrencyModelList(
    favourites: List<SymbolModel>
): List<CurrencyModel> {
    val favouritesSet = favourites.toSet()
    return this.map { domain -> domain.toCurrencyModel(isFavourite = favouritesSet.find { it.name == domain.name } != null) }
}

fun SymbolDomain.toSymbolModel(): SymbolModel {
    return SymbolModel(name = this.name)
}

fun List<SymbolDomain>.toSymbolModelList(): List<SymbolModel> {
    return this.map { it.toSymbolModel() }
}