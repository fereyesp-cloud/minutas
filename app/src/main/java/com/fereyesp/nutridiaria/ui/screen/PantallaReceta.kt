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
import com.fereyesp.nutridiaria.local.Minuta
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fereyesp.nutridiaria.local.AppDatabase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaReceta(
    tituloReceta: String,
    irAtras: () -> Unit
){
    val context = LocalContext.current
    val db = remember { AppDatabase.obtenerInstancia(context) }

    var receta by remember { mutableStateOf<Minuta?>(null) }

    LaunchedEffect(tituloReceta) {
        receta = db.minutaDao().buscarPorTitulo(tituloReceta)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalle de receta")
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
            receta?.let { recetaActual ->
                Text(text = recetaActual.dia, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                Text(text = recetaActual.titulo, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = recetaActual.recomendacionNutricional, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Ingredientes:", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text(text = recetaActual.ingredientes, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Preparación:", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                Text(text = recetaActual.pasos, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Receta: ${recetaActual.titulo}\n\n" +
                                        "Ingredientes: ${recetaActual.ingredientes}\n\n" +
                                        "Preparación: ${recetaActual.pasos}"
                            )
                        }
                        context.startActivity(Intent.createChooser(intent, "Compartir receta"))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Compartir receta")
                }
            } ?: Text("No se encontró la receta")

            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = irAtras) {
                Text("Volver")
            }
        }

    }
}