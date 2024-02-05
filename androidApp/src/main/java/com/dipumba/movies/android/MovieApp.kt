package com.demo.movies.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.demo.movies.android.common.Detail
import com.demo.movies.android.common.Home
import com.demo.movies.android.common.MovieAppBar
import com.demo.movies.android.common.movieDestinations
import com.demo.movies.android.detail.DetailScreen
import com.demo.movies.android.detail.DetailViewModel
import com.demo.movies.android.home.HomeScreen
import com.demo.movies.android.home.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
@Composable
fun MovieApp() {
    // Navigation controller for managing navigation between screens
    val navController = rememberNavController()

    // System UI controller to customize status bar color
    val systemUiController = rememberSystemUiController()

    // Scaffold state for managing scaffold-related functionality
    val scaffoldState = rememberScaffoldState()

    // Determine system dark theme and set status bar color accordingly
    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.Transparent
    }

//    // Set status bar color with proper handling of dark icons
//    SideEffect {
//        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
//
//    +
//
//    }

    // Get the current back stack entry to determine the current screen
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    // Scaffold composable for the overall structure
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // Custom AppBar for the movie app
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPaddings ->
        // NavHost for handling navigation between different screens
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ) {
            // Composable for the Home screen
            composable(Home.routeWithArgs) {
                // KoinViewModel for obtaining the HomeViewModel
                val homeViewModel: HomeViewModel = koinViewModel()

                // Composable for displaying the Home screen
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextMovies = {
                        homeViewModel.loadMovies(forceReload = it)
                    },
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            // Composable for the Detail screen
            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                // Extract movieId from arguments
                val movieId = it.arguments?.getInt("movieId") ?: 0

                // KoinViewModel for obtaining the DetailViewModel with parameters
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(movieId) }
                )

                // Composable for displaying the Detail screen
                DetailScreen(uiState = detailViewModel.uiState)
            }
        }
    }
}
