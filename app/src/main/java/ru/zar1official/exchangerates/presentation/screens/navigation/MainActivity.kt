package ru.zar1official.exchangerates.presentation.theme.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.zar1official.exchangerates.domain.usecases.AddFavouriteUseCase
import ru.zar1official.exchangerates.presentation.theme.ExchangeRatesTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var useCase: AddFavouriteUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesTheme {
                MainScreen()
            }
        }
    }
}
