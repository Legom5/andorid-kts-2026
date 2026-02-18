package com.legom.andoridkts2026

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ){
        composable(Screen.Main.route){
            MainScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Login.route){
            LoginScreen()
        }
    }
}

sealed class Screen(val route: String) {

    data object Main : Screen("main")

    data object Login : Screen("login")
}