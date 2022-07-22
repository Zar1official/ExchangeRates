package ru.zar1official.exchangerates.presentation.theme.screens.popular

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zar1official.exchangerates.domain.AddFavouriteUseCase
import ru.zar1official.exchangerates.domain.RemoveFavouriteUseCase
import ru.zar1official.exchangerates.presentation.screens.CurrencyModel
import ru.zar1official.exchangerates.presentation.theme.screens.contract.ScreenViewModel
import ru.zar1official.exchangerates.update
import javax.inject.Inject

@HiltViewModel
class PopularScreenViewModel @Inject constructor(
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) :
    ScreenViewModel<PopularScreenState, PopularScreenEvent, PopularScreenIntent>() {
    override val _screenState: MutableStateFlow<PopularScreenState> =
        MutableStateFlow(
            PopularScreenState(
                currencies = listOf(
                    CurrencyModel("fkfk", 100f, false),
                    CurrencyModel("k", 100f, true),
                    CurrencyModel("ffk", 100f, false)
                )
            )
        )

    override fun onSendIntent(intent: PopularScreenIntent) {
        when (intent) {
            is PopularScreenIntent.ClickFavourite -> {
                viewModelScope.launch {
                    val oldModel = intent.model
                    val newModel = oldModel.copy(isFavourite = !oldModel.isFavourite)
                    if (oldModel.isFavourite) {
                        removeFavouriteUseCase()
                    } else {
                        addFavouriteUseCase()
                    }
                    _screenState.update {
                        it.copy(
                            currencies = it.currencies.update(
                                oldModel,
                                newModel
                            )
                        )
                    }
                }
            }
        }
    }
}