package ru.zar1official.exchangerates.domain.models

sealed class SortDomain {
    object AlphabetAsc : SortDomain()
    object AlphabetDesc : SortDomain()
    object RateAsc : SortDomain()
    object RateDesc : SortDomain()
}