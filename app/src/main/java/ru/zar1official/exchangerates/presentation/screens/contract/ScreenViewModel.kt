package ru.zar1official.exchangerates.presentation.theme.screens.contract

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class ScreenViewModel<State : ScreenState, Event : ScreenEvent, Intent : ScreenIntent> :
    ViewModel() {
    protected val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    protected abstract val _screenState: MutableStateFlow<State>
    val screenState: StateFlow<State> get() = _screenState.asStateFlow()

    abstract fun onSendIntent(intent: Intent)
}