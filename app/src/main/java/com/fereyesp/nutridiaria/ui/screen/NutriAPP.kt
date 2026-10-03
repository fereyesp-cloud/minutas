package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import android.net.Uri
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.fereyesp.nutridiaria.preferences.SesionPreferences


@Composable
fun NutriDiarioApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sesion = remember { SesionPreferences(context) }

    val nombreGuardado = sesion.obtenerNombreUsuario()
    var esAdminActual by remember { mutableStateOf(sesion.esAdmin()) }

    val destinoInicial = if (nombreGuardado != null) {
        "minuta/$nombreGuardado"
    } else {
        "login"
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(
                irARegistro = { navController.navigate("registro") },
                irARecuperar = { navController.navigate("recuperar") },
                irAMinuta = {},
                onIniciarSesion = { nombre, esAdmin ->
                    esAdminActual = esAdmin
                    navController.navigate("minuta/$nombre")
                }
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
                esAdmin = esAdminActual,
                onCerrarSession = {
                    sesion.cerrarSesion()
                    navController.navigate("login") {
                        popUpTo("login") {inclusive = true}
                    }
                },
                irAMiPerfil = {
                    navController.navigate("perfil/$nombre")
                },
                irAReceta = { titulo -> navController.navigate("receta/${Uri.encode(titulo)}") },
                irAAdmin = {
                    navController.navigate("admin")
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
        composable(
            route = "receta/{titulo}",
            arguments = listOf(navArgument("titulo") { type = NavType.StringType })
        ) { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
            PantallaReceta(
                tituloReceta = titulo,
                irAtras = { navController.popBackStack() }
            )
        }
        composable("admin") {
            PantallaAdmin(
                irAtras = { navController.popBackStack() }
            )
        }
    }
}