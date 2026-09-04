package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fereyesp.nutridiaria.data.usuarios

/**
 * Pantalla de perfil de usuario logueado
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiPerfil(
    nombreUsuario: String,
    irAtras: () -> Unit
) {
    val usuarioEncontrado = usuarios.find { it.nombre == nombreUsuario }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi perfil") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (usuarioEncontrado != null) {
                Text(
                    text = "Nombre: ${usuarioEncontrado.nombre}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Usuario: ${usuarioEncontrado.usuario}",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text("No se encontró información del usuario.")
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = irAtras) {
                Text("Volver")
            }
        }
    }
}