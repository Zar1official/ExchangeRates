package ru.zar1official.exchangerates.presentation.theme.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.zar1official.exchangerates.presentation.screens.navigation.Screens
import ru.zar1official.exchangerates.presentation.theme.screens.popular.UniversalRatesScreen
import ru.zar1official.exchangerates.presentation.viewModels.FavouriteScreenViewModel
import ru.zar1official.exchangerates.presentation.viewModels.PopularScreenViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(topBar = {
        TopAppBar() {
//            Text(text = stringResource(id = R.strin))
        }
    }, bottomBar = {
        BottomBar(navController = navController)
    }, scaffoldState = scaffoldState) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BottomNavGraph(navController = navController, scaffoldState = scaffoldState)
        }
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
            val vm = hiltViewModel<PopularScreenViewModel>()
            UniversalRatesScreen(scaffoldState = scaffoldState, viewModel = vm)
        }
        composable(route = Screens.FavouriteScreen.route) {
            val vm = hiltViewModel<FavouriteScreenViewModel>()
            UniversalRatesScreen(scaffoldState = scaffoldState, viewModel = vm)
        }
    }
}