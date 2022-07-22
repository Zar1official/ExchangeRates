package ru.zar1official.exchangerates.presentation.screens.popular.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.zar1official.exchangerates.presentation.screens.CurrencyModel

@Composable
fun CurrencyLazyList(items: List<CurrencyModel>, onClick: (model: CurrencyModel) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(items, key = { model -> model.name }) {
            CurrencyItem(currencyModel = it, onFavouriteClicked = onClick)
        }
    }
}
