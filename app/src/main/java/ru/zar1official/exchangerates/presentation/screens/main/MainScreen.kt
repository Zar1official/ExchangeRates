package ru.zar1official.exchangerates.presentation.theme.screens.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.zar1official.exchangerates.R
import ru.zar1official.exchangerates.presentation.theme.screens.favourites.FavouritesScreen
import ru.zar1official.exchangerates.presentation.theme.screens.main.navigation.Screens
import ru.zar1official.exchangerates.presentation.theme.screens.popular.PopularScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
    }, bottomBar = {
        BottomBar(navController = navController)
    }, scaffoldState = scaffoldState) {
        BottomNavGraph(navController = navController, scaffoldState = scaffoldState)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    BottomNavigation {
        val screens = listOf(Screens.PopularScreen, Screens.FavouriteScreen)
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = false,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        popUpTo(screen.route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.contentDescription)
                    )
                },
                label = { Text(text = stringResource(id = screen.title)) })
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(navController = navController, startDestination = Screens.PopularScreen.route) {
        composable(
            route = Screens.PopularScreen.route,
        ) {
            PopularScreen()
        }
        composable(route = Screens.FavouriteScreen.route) {
            FavouritesScreen()
        }
    }
}