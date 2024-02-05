package com.demo.movies.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

// Interface representing a navigation destination
interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

// Object representing the Home destination
object Home : Destination {
    override val title: String
        get() = "Movies"

    override val route: String
        get() = "home"

    override val routeWithArgs: String
        get() = route
}

// Object representing the Detail destination
object Detail : Destination {
    override val title: String
        get() = "Movie details"

    override val route: String
        get() = "detail"

    override val routeWithArgs: String
        get() = "$route/{movieId}"

    // List of arguments associated with the Detail destination
    val arguments = listOf(
        navArgument(name = "movieId") { type = NavType.IntType }
    )
}

// List of all movie destinations
val movieDestinations = listOf(Home, Detail)
