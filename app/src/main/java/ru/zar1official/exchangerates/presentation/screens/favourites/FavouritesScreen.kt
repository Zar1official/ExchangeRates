package ru.zar1official.exchangerates.presentation.theme.screens.favourites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zar1official.exchangerates.presentation.screens.popular.components.CurrencyLazyList

@Composable
fun FavouritesScreen(viewModel: FavouriteScreenViewModel = hiltViewModel()) {
    val state by viewModel.screenState.collectAsState()

    CurrencyLazyList(items = state.currencies, onClick = { model ->
        viewModel.onSendIntent(FavouriteScreenIntent.ClickFavourite(model))
    })
}