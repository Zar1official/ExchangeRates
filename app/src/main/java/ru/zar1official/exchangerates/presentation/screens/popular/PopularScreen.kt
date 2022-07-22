package ru.zar1official.exchangerates.presentation.theme.screens.popular

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.screens.popular.components.CurrencyItem
import ru.zar1official.exchangerates.presentation.screens.popular.components.CurrencyLazyList

@Composable
fun PopularScreen(viewModel: PopularScreenViewModel = hiltViewModel()) {
    val state by viewModel.screenState.collectAsState()

    CurrencyLazyList(items = state.currencies) { model ->
        viewModel.onSendIntent(PopularScreenIntent.ClickFavourite(model))
    }
}