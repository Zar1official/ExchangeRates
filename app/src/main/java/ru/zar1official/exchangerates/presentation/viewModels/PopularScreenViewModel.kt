package ru.zar1official.exchangerates.presentation.viewModels

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zar1official.exchangerates.R
import ru.zar1official.exchangerates.domain.usecases.*
import ru.zar1official.exchangerates.update
import ru.zar1official.exchangerates.util.toCurrencyModelList
import ru.zar1official.exchangerates.util.toSortDomain
import ru.zar1official.exchangerates.util.toSymbolDomain
import ru.zar1official.exchangerates.util.toSymbolModelList
import javax.inject.Inject

@HiltViewModel
class PopularScreenViewModel @Inject constructor(
    private val getRatesWithBaseUseCase: GetRatesWithBaseUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val getAllSymbolsUseCase: GetAllSymbolsUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val sortCurrenciesByUseCase: SortCurrenciesByUseCase,
    observeFavouriteSymbolsUseCase: ObserveFavouriteSymbolsUseCase,
) : ScreenViewModel(observeFavouriteSymbolsUseCase = observeFavouriteSymbolsUseCase) {
    override val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState())

    override fun onSendIntent(intent: ScreenIntent) {
        when (intent) {
            is ScreenIntent.SelectSymbolItem -> {
                val symbol = intent.model
                _screenState.update { state -> state.copy(selectedSymbol = symbol) }
                viewModelScope.launch {
                    getRatesWithBaseUseCase(symbol.name).onSuccess { newRates ->
                        val favourites = _screenState.value.favourites
                        val sortOption = _screenState.value.sortBy

                        val sortedRates = sortCurrenciesByUseCase(
                            sort = sortOption.toSortDomain(),
                            data = newRates.toCurrencyModelList(favourites = favourites)
                        )

                        _screenState.update { state -> state.copy(currencies = sortedRates) }
                    }.onFailure {
                        _event.emit(ScreenEvent.ShowSnackBarEvent(message = R.string.network_error))
                    }
                }
            }

            is ScreenIntent.ClickFavouriteButton -> {
                viewModelScope.launch {
                    val model = intent.model
                    val newModel = model.copy(isFavourite = !model.isFavourite)
                    val currencies = _screenState.value.currencies
                    val updatedCurrencies = currencies.update(model, newModel)

                    if (model.isFavourite) {
                        removeFavouriteUseCase(model.toSymbolDomain())
                    } else {
                        addFavouriteUseCase(model.toSymbolDomain())
                    }
                    _screenState.update { it.copy(currencies = updatedCurrencies) }
                }
            }

            is ScreenIntent.SelectSortOption -> {
                val sortModel = intent.model
                _screenState.update { state ->
                    state.copy(
                        currencies = sortCurrenciesByUseCase(
                            sort = sortModel.toSortDomain(),
                            data = state.currencies
                        ),
                        sortBy = sortModel
                    )
                }
            }
        }
    }

    override fun loadRates() {
        viewModelScope.launch {
            val selectedSymbol = _screenState.value.selectedSymbol
            val favourites = _screenState.value.favourites
            val symbolsTask = async { getAllSymbolsUseCase() }
            val ratesTask =
                async { getRatesWithBaseUseCase(base = selectedSymbol.name) }
            val symbols = symbolsTask.await()
            val rates = ratesTask.await()

            symbols.onSuccess { s ->
                rates.onSuccess { r ->
                    _screenState.update { state ->
                        state.copy(
                            currencies = r.toCurrencyModelList(favourites = favourites),
                            symbols = s.toSymbolModelList(),
                            isLoaded = true
                        )
                    }
                }.onFailure {
                    _event.emit(ScreenEvent.ShowSnackBarEvent(R.string.network_error))
                }
            }.onFailure {
                _event.emit(ScreenEvent.ShowSnackBarEvent(R.string.network_error))
            }
        }
    }
}