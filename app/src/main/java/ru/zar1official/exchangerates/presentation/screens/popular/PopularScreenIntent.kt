package ru.zar1official.exchangerates.presentation.theme.screens.popular

import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenIntent

sealed class PopularScreenIntent : ScreenIntent {
    data class ClickFavourite(val model: CurrencyModel) : PopularScreenIntent()
}
