package ru.zar1official.exchangerates.presentation.viewModels

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.zar1official.exchangerates.domain.usecases.ObserveFavouriteSymbolsUseCase
import ru.zar1official.exchangerates.presentation.models.CurrencyModel
import ru.zar1official.exchangerates.presentation.models.SortModel
import ru.zar1official.exchangerates.presentation.models.SymbolModel
import ru.zar1official.exchangerates.util.toSymbolModelList

abstract class ScreenViewModel(
    protected val observeFavouriteSymbolsUseCase: ObserveFavouriteSymbolsUseCase
) :
    ViewModel() {
    data class ScreenState(
        val isLoaded: Boolean = false,
        val currencies: List<CurrencyModel> = listOf(),
        val favourites: List<SymbolModel> = listOf(),
        val symbols: List<SymbolModel> = listOf(),
        val selectedSymbol: SymbolModel = SymbolModel(name = DEFAULT_SELECTED_SYMBOL_NAME),
        val sortBy: SortModel = SortModel.AlphabetAsc
    )

    companion object {
        private const val DEFAULT_SELECTED_SYMBOL_NAME = "USD"
    }

    sealed class ScreenIntent {
        data class ClickFavouriteButton(val model: CurrencyModel) : ScreenIntent()
        data class SelectSymbolItem(val model: SymbolModel) : ScreenIntent()
        data class SelectSortOption(val model: SortModel) : ScreenIntent()
    }

    sealed class ScreenEvent {
        data class ShowSnackBarEvent(
            @StringRes val message: Int,
            val repeatAction: Boolean = false
        ) : ScreenEvent()

        data class ShowSnackBarEventText(
            val message: String,
            val repeatAction: Boolean = false
        ) : ScreenEvent()
    }

    init {
        observeFavourites()
    }

    private fun observeFavourites() {
        viewModelScope.launch {
            observeFavouriteSymbolsUseCase().map { favouriteSymbols -> favouriteSymbols.toSymbolModelList() }
                .collectIndexed { index, newList ->
                    _screenState.update { state -> state.copy(favourites = newList) }
                    if (index == 0) {
                        loadRates()
                    }
                }
        }
    }

    abstract fun loadRates()
    abstract fun onSendIntent(intent: ScreenIntent)

    protected val _event = MutableSharedFlow<ScreenEvent>()
    val event: SharedFlow<ScreenEvent> = _event.asSharedFlow()

    protected abstract val _screenState: MutableStateFlow<ScreenState>
    val screenState: StateFlow<ScreenState> get() = _screenState.asStateFlow()
}
