package ru.zar1official.exchangerates.presentation.theme.screens.popular

import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenState

data class PopularScreenState(
    val isListLoading: Boolean = false,
    val currencies: List<CurrencyModel> = listOf()

) : ScreenState