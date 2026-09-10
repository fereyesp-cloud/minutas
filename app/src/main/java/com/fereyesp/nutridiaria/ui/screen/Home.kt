package com.fereyesp.nutridiaria.ui.screen

import android.se.omapi.Session
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fereyesp.nutridiaria.minutas


/**
 * Pantalla de la visualizacion de la minuta
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMinuta(
    nombreUsuario: String,
    onCerrarSession: () -> Unit,
    irAMiPerfil: () -> Unit
) {

    var diaSeleccionado by remember { mutableStateOf("Selecciona un día") }
    var busquedaIngredientes by remember { mutableStateOf("") }
    var menuExpandido by remember { mutableStateOf(false) }

    val recetasConMuchosIngredientes = minutas.any { it.cantidadIngredientes > 5 }
    val totalConAvena = minutas.count { it.contieneIngredientes("avena") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu minuta semanal") },
                actions = {
                    IconButton(onClick = { menuExpandido = true }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = menuExpandido,
                        onDismissRequest = { menuExpandido = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Mi perfil") },
                            onClick = {
                                menuExpandido = false
                                irAMiPerfil()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Cerrar sesión") },
                            onClick = {
                                menuExpandido = false
                                onCerrarSession()
                            }
                        )
                    }
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
                .padding(16.dp)
        ) {
            Text(
                text = "¡Bienvenido, $nombreUsuario!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            SelectorDia(
                diaSeleccionado = diaSeleccionado,
                onDiaSeleccionado = { diaSeleccionado = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Buscar receta",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = busquedaIngredientes,
                onValueChange = { busquedaIngredientes = it },
                label = { Text("Buscar por ingrediente") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            val recetasFiltradas = minutas
                .filter { it.dia == diaSeleccionado }
                .filter { busquedaIngredientes.isEmpty() || it.contieneIngredientes(busquedaIngredientes) }

            Text(
                text = "Recetas actuales: ${recetasFiltradas.size}",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(8.dp))


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(recetasFiltradas) { receta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = receta.dia,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = receta.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = receta.recomendacionNutricional,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Ingredientes:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = receta.ingredientes,
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(
                                text =  "Cantidad de ingredenetes en la receta: ${receta.cantidadIngredientes}",
                                style = MaterialTheme.typography.bodySmall
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Preparación:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = receta.pasos,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

/*
* Logica de seleccion de minuta por dia de la semana
* */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorDia(
    diaSeleccionado: String,
    onDiaSeleccionado: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }
    val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = !expandido }
    ) {
        OutlinedTextField(
            value = diaSeleccionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Selecciona un día") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            dias.forEach { dia ->
                DropdownMenuItem(
                    text = { Text(dia) },
                    onClick = {
                        onDiaSeleccionado(dia)
                        expandido = false
                    }
                )
            }
        }
    }
}



