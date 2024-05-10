package com.example.ensemblecodesample.core.navigation

sealed class Screen(val route : String) {
    data object SearchScreen : Screen("search_screen")
}
