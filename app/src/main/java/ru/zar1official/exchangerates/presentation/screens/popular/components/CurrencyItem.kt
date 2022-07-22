package ru.zar1official.exchangerates.presentation.screens.popular.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zar1official.exchangerates.R
import ru.zar1official.exchangerates.presentation.screens.CurrencyModel

@Composable
fun CurrencyItem(currencyModel: CurrencyModel, onFavouriteClicked: (model: CurrencyModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), shape = RoundedCornerShape(5.dp),
        elevation = 2.dp,
        backgroundColor = Color.Gray.copy(alpha = 0.05f)
    ) {
        Row(
            modifier = Modifier.padding(end = 20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { onFavouriteClicked(currencyModel) }) {
                Icon(
                    modifier = Modifier.fillMaxSize(1f),
                    painter = if (!currencyModel.isFavourite) painterResource(id = R.drawable.ic_favourite_border) else painterResource(
                        id = R.drawable.ic_favourite
                    ),
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = ""
                )
            }
        }

        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${currencyModel.name}: ", style = TextStyle(fontSize = 20.sp))
            Spacer(modifier = Modifier.width(20.dp))
            Text("${currencyModel.rate}", style = TextStyle(fontSize = 20.sp))
        }
    }
}