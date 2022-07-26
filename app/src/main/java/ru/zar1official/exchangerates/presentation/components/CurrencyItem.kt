package ru.zar1official.exchangerates.presentation.components

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
import ru.zar1official.exchangerates.presentation.models.CurrencyModel

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    currencyModel: CurrencyModel,
    onFavouriteClicked: (model: CurrencyModel) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
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
                    modifier = Modifier.size(40.dp),
                    painter = if (!currencyModel.isFavourite) painterResource(id = R.drawable.ic_favourite_border)
                    else painterResource(
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
            Text(text = "${currencyModel.rate}", style = TextStyle(fontSize = 20.sp))
        }
    }
}