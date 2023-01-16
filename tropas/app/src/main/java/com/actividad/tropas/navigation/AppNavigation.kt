package com.actividad.tropas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.actividad.tropas.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.MenuInicio.ruta)

    {
        composable(AppScreens.MenuInicio.ruta) {MenuInicio(navigationController) }
        composable(AppScreens.Guardartropas.ruta) {Guardartropas(navigationController) }
        composable(AppScreens.Modificartropas.ruta) {Modificartropas(navigationController) }
        composable(AppScreens.Borrartropas.ruta) { Borrartropas(navigationController) }
        composable(AppScreens.Consultartropas.ruta) {Consultartropas(navigationController) }
        composable(AppScreens.Informetropas.ruta) {Informetropas(navigationController) }
    }
}