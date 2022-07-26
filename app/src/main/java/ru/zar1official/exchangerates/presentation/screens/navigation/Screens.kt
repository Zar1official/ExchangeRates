package ru.zar1official.exchangerates.presentation.screens.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.zar1official.exchangerates.R
import ru.zar1official.exchangerates.presentation.PresentationConstants

sealed class Screens(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
) {
    object FavouriteScreen : Screens(
        route = PresentationConstants.FAVOURITE_SCREEN_ROUTE,
        title = R.string.favourite_screen_title,
        icon = R.drawable.ic_favourite,
        contentDescription = R.string.favourite_screen_title
    )

    object PopularScreen : Screens(
        route = PresentationConstants.POPULAR_SCREEN_ROUTE,
        title = R.string.popular_screen_title,
        icon = R.drawable.ic_popular,
        contentDescription = R.string.popular_screen_title
    )
}