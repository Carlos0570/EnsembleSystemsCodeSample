package com.example.ensemblecodesample.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ensemblecodesample.search.SearchScreen
import com.example.ensemblecodesample.search.SearchViewModel

@Composable
fun OpenMovieDbNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SearchScreen.route) {

        composable(route = Screen.SearchScreen.route) {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(searchViewModel = searchViewModel)
        }
    }
}