package com.actividad.tropas.navigation

// CREAMOS UNA SEALED CLASS PARA CONTENER LOS OBJETOS DE CADA RUTA

sealed class AppScreens(val ruta: String){
    object MenuInicio: AppScreens("MenuInicio")
    object Guardartropas: AppScreens("Guardartropas")
    object Modificartropas: AppScreens("Modificartropas")
    object Borrartropas: AppScreens("Borrartropas")
    object Consultartropas: AppScreens("Consultartropas")
    object Informetropas: AppScreens("Informetropas")
    }
