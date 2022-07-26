package ru.zar1official.exchangerates.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zar1official.exchangerates.presentation.models.CurrencyModel

@Composable
fun CurrencySection(items: List<CurrencyModel>, onClick: (model: CurrencyModel) -> Unit) {
    val state = rememberLazyListState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp),
        state = state
    ) {
        items(count = items.size) {
            CurrencyItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                currencyModel = items[it],
                onFavouriteClicked = onClick,
            )
        }
    }
}
