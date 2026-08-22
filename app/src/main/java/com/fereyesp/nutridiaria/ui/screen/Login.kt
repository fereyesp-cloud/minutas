package com.fereyesp.nutridiaria.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.os.VibrationEffect
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fereyesp.nutridiaria.R
import androidx.compose.ui.platform.LocalContext
import android.os.Vibrator
import android.media.ToneGenerator
import com.fereyesp.nutridiaria.data.usuarios
import androidx.compose.material3.AlertDialog

/*
* Pantalla de login, donde se valida las credenciales
* Redirige a la pantalla ¿Olvidaste tu contraseña? y ¿No tienes cuenta? Registrate"
* */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    irARegistro: () -> Unit,
    irARecuperar: () -> Unit,
    irAMinuta: () -> Unit,
    onIniciarSesion: (String) -> Unit
) {

    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf(value = "") }
    var mostrarError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (mostrarError) {
        AlertDialog(
            onDismissRequest = { mostrarError = false },
            title = { Text("Error") },
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
                title = {
                    Text("Bienvenidos a tu minuta")
                },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(
                    id = R.drawable.usuario
                ),
                contentDescription = "Login",
                modifier = Modifier.height(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = {usuario = it},
                label = {Text("usuario")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = {contrasena = it},
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val usuarioEncontrado = usuarios.find {
                        it.usuario == usuario && it.contrasena == contrasena
                    }

                    if (usuarioEncontrado != null) {
                        // alerta auditiva
                        val tono = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
                        tono.startTone(ToneGenerator.TONE_PROP_ACK, 200)

                        onIniciarSesion(usuarioEncontrado.nombre)
                    } else {
                        mensajeError = "Usuario no encontrado"
                        mostrarError = true

                        // vibración
                        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "ingresar", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))


            TextButton(
                onClick = irARecuperar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            TextButton(
                onClick = irARegistro,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¿No tienes cuenta? Registrate",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

        }
    }
}
