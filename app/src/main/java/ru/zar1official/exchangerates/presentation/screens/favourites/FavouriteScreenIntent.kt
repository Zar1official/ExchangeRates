package ru.zar1official.exchangerates.presentation.theme.screens.favourites

import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenIntent

sealed class FavouriteScreenIntent: ScreenIntent {
    data class ClickFavourite(val model: CurrencyModel) : FavouriteScreenIntent()
}