package ru.zar1official.exchangerates.presentation.theme.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.zar1official.exchangerates.presentation.theme.ExchangeRatesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRatesTheme {
                MainScreen()
            }
        }
    }
}
