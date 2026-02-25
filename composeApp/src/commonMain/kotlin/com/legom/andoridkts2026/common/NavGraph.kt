package com.legom.andoridkts2026.common

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.legom.andoridkts2026.feature.login.presentation.LoginScreen
import com.legom.andoridkts2026.feature.main.presentation.MainScreen
import com.legom.andoridkts2026.feature.onboard.presentation.OnboardScreen
import kotlinx.serialization.Serializable

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Onboard
    ) {
        composable<Onboard> {
            OnboardScreen(
                onNavigateToLogin = {
                    navController.navigate(Login)
                }
            )
        }

        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Main){
                        popUpTo(Onboard) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Main> {
            MainScreen()
        }
    }
}

@Serializable
object Onboard

@Serializable
object Login

@Serializable
object Main
