package ru.zar1official.exchangerates.presentation.theme.screens.popular

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import ru.zar1official.exchangerates.presentation.components.CurrencySection
import ru.zar1official.exchangerates.presentation.components.LoadingSection
import ru.zar1official.exchangerates.presentation.components.SelectSymbolSection
import ru.zar1official.exchangerates.presentation.components.SortOptionsSection
import ru.zar1official.exchangerates.presentation.models.SortModel
import ru.zar1official.exchangerates.presentation.viewModels.ScreenViewModel

@Composable
fun UniversalRatesScreen(
    viewModel: ScreenViewModel,
    scaffoldState: ScaffoldState
) {
    val state by viewModel.screenState.collectAsState()
    val resources = LocalContext.current.resources
    val sortOptions by remember {
        mutableStateOf(
            listOf(
                SortModel.AlphabetAsc,
                SortModel.AlphabetDesc,
                SortModel.RateDesc,
                SortModel.RateAsc
            )
        )
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ScreenViewModel.ScreenEvent.ShowSnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = resources.getString(event.message),
                        duration = SnackbarDuration.Short,
                    )
                }

                is ScreenViewModel.ScreenEvent.ShowSnackBarEventText -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    })

    if (state.isLoaded) {
        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                SelectSymbolSection(
                    modifier = Modifier.weight(1f),
                    symbols = state.symbols,
                    selectedSymbol = state.selectedSymbol,
                    onSymbolSelected = { symbolModel ->
                        viewModel.onSendIntent(ScreenViewModel.ScreenIntent.SelectSymbolItem(model = symbolModel))
                    })

                Box(modifier = Modifier.weight(0.05f)) {
                    Spacer(modifier = Modifier.fillMaxWidth())
                }

                SortOptionsSection(
                    modifier = Modifier.weight(0.1f),
                    options = sortOptions,
                    selectedOption = state.sortBy,
                    onSelectOption = { sortModel ->
                        viewModel.onSendIntent(
                            ScreenViewModel.ScreenIntent.SelectSortOption(
                                model = sortModel
                            )
                        )
                    }
                )
            }
            CurrencySection(items = state.currencies, onClick = { model ->
                viewModel.onSendIntent(ScreenViewModel.ScreenIntent.ClickFavouriteButton(model = model))
            })
        }
    } else {
        LoadingSection()
    }
}