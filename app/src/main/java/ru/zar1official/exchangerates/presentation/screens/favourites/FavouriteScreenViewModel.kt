package ru.zar1official.exchangerates.presentation.theme.screens.favourites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zar1official.exchangerates.domain.RemoveFavouriteUseCase
import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) :
    ScreenViewModel<FavouriteScreenState, FavouriteScreenEvent, FavouriteScreenIntent>() {

    override fun onSendIntent(intent: FavouriteScreenIntent) {
        when (intent) {
            is FavouriteScreenIntent.ClickFavourite -> {
                viewModelScope.launch {
                    removeFavouriteUseCase()
                    _screenState.update { it.copy(currencies = it.currencies.minusElement(intent.model)) }
                }
            }
        }
    }

    override val _screenState: MutableStateFlow<FavouriteScreenState> =
        MutableStateFlow(
            FavouriteScreenState(
                currencies = listOf(
                    CurrencyModel("fkfk", 100f, false),
                    CurrencyModel("k", 100f, true),
                    CurrencyModel("ffk", 100f, false)
                )
            )
        )


}