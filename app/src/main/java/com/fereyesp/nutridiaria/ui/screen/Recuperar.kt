package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import com.fereyesp.nutridiaria.data.usuarios
import androidx.compose.material.icons.filled.Warning

/*
* Pantalla para recuperar usuario
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRecuperar(irALogin: () -> Unit) {

    var nombre by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var mostrarExito by remember { mutableStateOf(false) }
    var mostrarError by remember { mutableStateOf(false) }
    var correo by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    /*
    /Modal de exito, usuario encontrado
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
            text = { Text("Se ha enviado un correo para la recuperación de la cuenta") },
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

    /*
* Modal de error (usuario no encontrado o correo inválido)
* */
    if (mostrarError) {
        AlertDialog(
            onDismissRequest = { mostrarError = false },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(40.dp)
                )
            },
            title = {
                Text(
                    text = "Error",
                    color = MaterialTheme.colorScheme.error
                )
            },
            text = { Text(mensajeError) },
            confirmButton = {
                TextButton(onClick = { mostrarError = false }) {
                    Text("Aceptar")
                }
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar contraseña") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Top,
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {

                    val errorCorreo = validarCampo(correo, { it.esCorreoValido() }, "El correo no tiene un formato válido")

                    if (errorCorreo != null) {
                        mensajeError  = errorCorreo
                        mostrarError = true
                    } else {
                        val usuarioEncontrado = usuarios.find {it.usuario == nombreUsuario}

                        if (usuarioEncontrado != null){
                            val tono = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
                            tono.startTone(ToneGenerator.TONE_PROP_ACK, 200)
                            mostrarExito = true
                        } else {
                            mensajeError  = "Usuario no encontrado"
                            mostrarError = true
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Recuperar contraseña")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = irALogin) {
                Text("Volver a inicio de sesión")
            }
        }
    }
}

// //Funcion de extension para validar correo
fun String.esCorreoValido(): Boolean {
    return this.contains("@") && this.contains(".")
}