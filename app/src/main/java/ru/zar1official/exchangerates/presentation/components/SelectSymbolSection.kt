package ru.zar1official.exchangerates.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.zar1official.exchangerates.presentation.models.SymbolModel

@Composable
fun SelectSymbolSection(
    modifier: Modifier = Modifier,
    symbols: List<SymbolModel>,
    selectedSymbol: SymbolModel,
    onSymbolSelected: (model: SymbolModel) -> Unit
) {
    Box(modifier = modifier) {
        LazyRow(modifier = Modifier.fillMaxWidth(), content = {
            items(symbols) { symbol ->
                Text(
                    text = symbol.name,
                    modifier = Modifier.clickable {
                        onSymbolSelected(symbol)
                    },
                    fontWeight = if (selectedSymbol == symbol) FontWeight.Bold else FontWeight.Normal
                )
            }
        }, horizontalArrangement = Arrangement.spacedBy(15.dp))
    }
}

