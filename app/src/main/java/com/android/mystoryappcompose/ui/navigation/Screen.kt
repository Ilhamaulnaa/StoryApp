package com.android.mystoryappcompose.ui.navigation

sealed class Screen (val route: String){

    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
    object Detail: Screen("home/{playerId}"){
        fun createRoute(playerId: Int) = "home/$playerId"
    }

}