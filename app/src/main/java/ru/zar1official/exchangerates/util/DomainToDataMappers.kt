package ru.zar1official.exchangerates.util

import ru.zar1official.exchangerates.data.local.models.FavouriteCurrency
import ru.zar1official.exchangerates.domain.models.SymbolDomain

fun SymbolDomain.toFavouriteCurrency(): FavouriteCurrency {
    return FavouriteCurrency(
        name = this.name
    )
}

fun List<SymbolDomain>.convertToString(): String {
    return buildString {
        this@convertToString.forEach { this.append("${it.name},") }
    }.removePrefix(",")
}