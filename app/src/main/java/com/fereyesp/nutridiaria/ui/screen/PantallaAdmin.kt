package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fereyesp.nutridiaria.local.AppDatabase
import com.fereyesp.nutridiaria.local.Usuarios
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAdmin(irAtras: () -> Unit) {

    val context = LocalContext.current
    val db = remember { AppDatabase.obtenerInstancia(context) }
    val scope = rememberCoroutineScope()

    var usuarios by remember { mutableStateOf<List<Usuarios>>(emptyList()) }
    var usuarioEditando by remember { mutableStateOf<Usuarios?>(null) }
    var nombreEditado by remember { mutableStateOf("") }
    var usuarioAEliminar by remember { mutableStateOf<Usuarios?>(null) }

    LaunchedEffect(Unit) {
        usuarios = db.usuarioDao().obtenerTodos()
    }

    usuarioEditando?.let { usuario ->
        AlertDialog(
            onDismissRequest = { usuarioEditando = null },
            title = { Text("Editar usuario") },
            text = {
                OutlinedTextField(
                    value = nombreEditado,
                    onValueChange = { nombreEditado = it },
                    label = { Text("Nombre") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch {
                        db.usuarioDao().actualizar(usuario.copy(nombre = nombreEditado))
                        usuarios = db.usuarioDao().obtenerTodos()
                        usuarioEditando = null
                    }
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { usuarioEditando = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    usuarioAEliminar?.let { usuario ->
        AlertDialog(
            onDismissRequest = { usuarioAEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Seguro que deseas eliminar a ${usuario.nombre}?") },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch {
                        db.usuarioDao().eliminar(usuario)
                        usuarios = db.usuarioDao().obtenerTodos()
                        usuarioAEliminar = null
                    }
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { usuarioAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Administrar usuarios") },
                navigationIcon = {
                    IconButton(onClick = irAtras) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(usuario.nombre)
                        Text(usuario.usuario)

                        Button(onClick = {
                            usuarioAEliminar = usuario
                        }) {
                            Text("Eliminar")
                        }
                        Button(onClick = {
                            usuarioEditando = usuario
                            nombreEditado = usuario.nombre
                        }) {
                            Text("Editar")
                        }
                    }
                }
            }
        }
    }
}