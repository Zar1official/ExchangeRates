package ru.zar1official.exchangerates.util

import ru.zar1official.exchangerates.domain.models.SortDomain
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.presentation.models.CurrencyModel
import ru.zar1official.exchangerates.presentation.models.SortModel
import ru.zar1official.exchangerates.presentation.models.SymbolModel

fun CurrencyModel.toSymbolDomain(): SymbolDomain {
    return SymbolDomain(name = this.name)
}

fun SymbolModel.toSymbolDomain(): SymbolDomain {
    return SymbolDomain(name = this.name)
}

fun List<SymbolModel>.toSymbolDomainList(): List<SymbolDomain> {
    return this.map { it.toSymbolDomain() }
}

fun SortModel.toSortDomain(): SortDomain {
    return when (this) {
        is SortModel.AlphabetAsc -> SortDomain.AlphabetAsc
        is SortModel.AlphabetDesc -> SortDomain.AlphabetDesc
        is SortModel.RateAsc -> SortDomain.RateAsc
        is SortModel.RateDesc -> SortDomain.RateDesc
    }
}
