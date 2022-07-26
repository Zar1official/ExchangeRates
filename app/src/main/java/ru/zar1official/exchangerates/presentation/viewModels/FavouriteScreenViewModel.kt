package ru.zar1official.exchangerates.presentation.viewModels

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zar1official.exchangerates.R
import ru.zar1official.exchangerates.domain.usecases.*
import ru.zar1official.exchangerates.util.*
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val removeFavouriteUseCase: RemoveFavouriteUseCase,
    private val getRatesWithSymbolsUseCase: GetRatesWithSymbolsUseCase,
    private val sortCurrenciesByUseCase: SortCurrenciesByUseCase,
    private val getAllSymbolsUseCase: GetAllSymbolsUseCase,
    observeFavouriteSymbolsUseCase: ObserveFavouriteSymbolsUseCase
) :
    ScreenViewModel(observeFavouriteSymbolsUseCase = observeFavouriteSymbolsUseCase) {

    override fun onSendIntent(intent: ScreenIntent) {
        when (intent) {
            is ScreenIntent.ClickFavouriteButton -> {
                viewModelScope.launch {
                    val model = intent.model
                    removeFavouriteUseCase(model.toSymbolDomain())
                    _screenState.update { state ->
                        state.copy(
                            currencies = state.currencies.minusElement(
                                model
                            )
                        )
                    }
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

            is ScreenIntent.SelectSymbolItem -> {
                val symbol = intent.model
                val favourites = _screenState.value.favourites
                _screenState.update { state -> state.copy(selectedSymbol = symbol) }

                viewModelScope.launch {
                    getRatesWithSymbolsUseCase(
                        base = symbol.name,
                        symbols = favourites.toSymbolDomainList()
                    ).onSuccess { newRates ->
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

        }
    }

    override val _screenState: MutableStateFlow<ScreenState> =
        MutableStateFlow(ScreenState())

    override fun loadRates() {
        viewModelScope.launch {
            val favourites = _screenState.value.favourites
            val selectedSymbol = _screenState.value.selectedSymbol

            val symbolsTask = async { getAllSymbolsUseCase() }
            val ratesTask =
                async {
                    getRatesWithSymbolsUseCase(
                        base = selectedSymbol.name,
                        symbols = favourites.toSymbolDomainList()
                    )
                }
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