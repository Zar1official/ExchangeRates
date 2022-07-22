package ru.zar1official.exchangerates.presentation.theme.screens.favourites

import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenState

data class FavouriteScreenState(
    val isListLoading: Boolean = false,
    val currencies: List<CurrencyModel> = listOf()

) : ScreenState