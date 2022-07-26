package ru.zar1official.exchangerates.presentation.models

import androidx.annotation.StringRes
import ru.zar1official.exchangerates.R

sealed class SortModel(@StringRes val title: Int) {
    object AlphabetAsc : SortModel(R.string.alphabet_asc_title)
    object AlphabetDesc : SortModel(R.string.alphabet_desc_title)
    object RateAsc : SortModel(R.string.rate_asc_title)
    object RateDesc : SortModel(R.string.rate_desc_title)
}