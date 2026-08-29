package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.text.input.PasswordVisualTransformation
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color


/**
 * Pantalla recuperar contraseña
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistro(irALogin: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var contrasenaRepetida by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var tipoUsuario by remember { mutableStateOf("Dueña de casa") }
    var mostrarExito by remember { mutableStateOf(false) }

    /**
     * Modal de exito
     */

    if (mostrarExito) {
        AlertDialog(
            onDismissRequest = { mostrarExito = false },
            icon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Éxito",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(40.dp)
                )
            },
            title = {
                Text(
                    text = "Solicitud enviada",
                    color = Color(0xFF2E7D32)
                )
            },
            text = { Text("Tu cuenta ha sido registrada correctamente.") },
            confirmButton = {
                TextButton(onClick = {
                    mostrarExito = false
                    irALogin()
                }) {
                    Text("Aceptar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de usuario") },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Tipo de usuario", style = MaterialTheme.typography.labelLarge)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = tipoUsuario == "Dueña de casa",
                    onClick = { tipoUsuario = "Dueña de casa" }
                )
                Text("Dueña de casa")

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = tipoUsuario == "Nutricionista",
                    onClick = { tipoUsuario = "Nutricionista" }
                )
                Text("Nutricionista")

            }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = contrasenaRepetida,
                onValueChange = { contrasenaRepetida = it },
                label = { Text("Repetir contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.width(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = aceptaTerminos,
                    onCheckedChange = { aceptaTerminos = it }
                )
                Text("Acepto los términos y condiciones")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val tono = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
                    tono.startTone(ToneGenerator.TONE_PROP_ACK, 200)

                    mostrarExito = true},
                enabled = aceptaTerminos,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarme")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = irALogin) {
                Text("Volver a inicio de sesión")
            }
        }
    }
}