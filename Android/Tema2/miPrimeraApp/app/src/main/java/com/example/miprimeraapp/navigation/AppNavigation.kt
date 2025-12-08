package com.example.miprimeraapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miprimeraapp.ui.screens.FavoritesScreen
import com.example.miprimeraapp.ui.screens.DiscoverScreen
import com.example.miprimeraapp.ui.screens.HomeScreen
import com.example.miprimeraapp.ui.screens.ProfileScreen
import com.example.miprimeraapp.ui.screens.PremiumScreen



@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("discover") {
            // Pantalla de descubrimiento
            DiscoverScreen(navController = navController)
        }
        composable("favorites") {
            // Pantalla de favoritos
            FavoritesScreen(navController = navController)
        }
        composable("profile") {
            // Pantalla de perfil
            ProfileScreen(navController = navController)
        }
        composable("premium") {
            // Pantalla premium
            PremiumScreen(navController = navController)
        }
    }
}