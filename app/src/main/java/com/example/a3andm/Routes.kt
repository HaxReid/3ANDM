package com.example.a3andm

sealed class Routes(val route: String) {
    object Chargement : Routes("Chargement")
    object Accueil : Routes("Accueil")
    object Details : Routes("Details")
}