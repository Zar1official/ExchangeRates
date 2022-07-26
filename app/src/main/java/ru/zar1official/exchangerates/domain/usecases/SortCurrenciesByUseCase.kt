package ru.zar1official.exchangerates.domain.usecases

import ru.zar1official.exchangerates.domain.models.SortDomain
import ru.zar1official.exchangerates.presentation.models.CurrencyModel
import javax.inject.Inject

class SortCurrenciesByUseCase @Inject constructor() {
    operator fun invoke(sort: SortDomain, data: List<CurrencyModel>): List<CurrencyModel> {
        return when (sort) {
            is SortDomain.AlphabetAsc -> data.sortedBy { it.name }
            is SortDomain.AlphabetDesc -> data.sortedByDescending { it.name }
            is SortDomain.RateAsc -> data.sortedBy { it.rate }
            is SortDomain.RateDesc -> data.sortedByDescending { it.rate }
        }
    }
}