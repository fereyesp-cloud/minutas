package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

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
                onIniciarSesion = { nombre -> navController.navigate("minuta/$nombre") }
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
        composable(
            route = "minuta/{nombre}",
            arguments = listOf(navArgument("nombre") {type = NavType.StringType})
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            PantallaMinuta(
                nombreUsuario = nombre,
                onCerrarSession = {
                    navController.navigate("login") {
                        popUpTo("Login") {inclusive = true}
                    }
                },
                irAMiPerfil = {
                    navController.navigate("perfil/$nombre")
                }
            )
        }
        composable(
            route = "perfil/{nombre}",
            arguments = listOf(navArgument("nombre") {type = NavType.StringType})
        ) {
            backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            MiPerfil(
                nombreUsuario = nombre,
                irAtras = {navController.popBackStack()}
            )
        }
    }
}