package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NutriDiarioApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(
                irARegistro = { navController.navigate("registro") },
                irARecuperar = { navController.navigate("recuperar") },
                irAMinuta = {},
                onIniciarSesion = { nombre -> navController.navigate("minuta") }
            )
        }
        composable("registro") {
            PantallaRegistro(
                irALogin = { navController.navigate("login") }
            )
        }
        composable("recuperar") {
            PantallaRecuperar(
                irALogin = { navController.navigate("login") }
            )
        }
        composable("minuta") {
            PantallaMinuta()
        }
    }
}