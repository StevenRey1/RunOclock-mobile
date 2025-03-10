package com.example.runoclock

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "landingScreen") {
        composable("landingScreen") {
            LandingScreen(navController = navController)
        }
        composable("loginScreen") {
            LoginScreen(navController = navController)
        }
        composable("otpCodeScreen") {
            OTPCodeScreen(navController = navController)
        }
        composable("misRutinasScreen") {
            MisRutinasScreen(navController = navController)
        }
        composable("routineExecutionScreen"){
            RoutineExecutionScreen(navController = navController)
        }
        composable("staticsScreen"){
            StatisticsScreen(navController = navController)
        }
    }
}


