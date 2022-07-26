package ru.zar1official.exchangerates.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zar1official.exchangerates.presentation.models.SortModel

@Composable
fun SortOptionsSection(
    modifier: Modifier = Modifier,
    options: List<SortModel>,
    selectedOption: SortModel,
    onSelectOption: (option: SortModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }, imageVector = Icons.Default.Menu, contentDescription = ""
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(0.5f),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        )
        {
            options.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSelectOption(it)
                }) {
                    if (selectedOption == it) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = ""
                        )
                    } else {
                        Spacer(modifier = Modifier.width(Icons.Default.Check.defaultWidth))
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = it.title)
                    )
                }
            }
        }
    }
}