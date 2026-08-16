@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)
package com.fereyesp.nutridiaria

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fereyesp.nutridiaria.data.Minuta
import com.fereyesp.nutridiaria.ui.theme.NutriDiariaTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card

val minutas = listOf(
    Minuta(
        dia = "Lunes",
        titulo = "Avena trasnochada con frutos rojos y chía",
        ingredientes = "1/2 taza de avena, 1 cda de semillas de chía, 1/2 taza de leche o bebida vegetal, 1/2 taza de frutos rojos, 1 cdta de miel",
        pasos = "1. Mezclar la avena, la chía y la leche en un frasco. 2. Dejar reposar en el refrigerador toda la noche. 3. Servir en la mañana con los frutos rojos y la miel por encima.",
        recomendacionNutricional = "Aporta fibra soluble e insoluble que favorece la digestión y mantiene la saciedad por más tiempo."
    ),
    Minuta(
        dia = "Martes",
        titulo = "Ensalada de quinua con garbanzos y palta",
        ingredientes = "1 taza de quinua cocida, 1/2 taza de garbanzos cocidos, 1/2 palta en cubos, 1/2 tomate picado, jugo de 1 limón, sal y aceite de oliva",
        pasos = "1. En un bol, mezclar la quinua fría y los garbanzos. 2. Agregar el tomate y la palta. 3. Aliñar con el jugo de limón, un chorrito de aceite de oliva y sal al gusto.",
        recomendacionNutricional = "Excelente fuente de proteína vegetal de alto valor biológico y grasas saludables para el corazón."
    ),
    Minuta(
        dia = "Miércoles",
        titulo = "Filete de salmón al horno con verduras",
        ingredientes = "150g de filete de salmón, 1/2 zapallo italiano picado, 1/2 pimentón en tiras, 1 cdta de aceite de oliva, orégano, sal y pimienta",
        pasos = "1. Precalentar el horno a 180°C. 2. Colocar las verduras y el salmón en una bandeja para horno. 3. Condimentar con aceite de oliva, orégano, sal y pimienta. 4. Hornear durante 15-20 minutos.",
        recomendacionNutricional = "Alto contenido de ácidos grasos Omega-3, fundamentales para la salud cardiovascular y cerebral."
    ),
    Minuta(
        dia = "Jueves",
        titulo = "Salteado de pollo con brócoli y arroz integral",
        ingredientes = "150g de pechuga de pollo en cubos, 1 taza de árboles de brócoli, 1/2 taza de arroz integral cocido, 1 cda de salsa de soya baja en sodio",
        pasos = "1. Cocinar el pollo en un sartén antiadherente con un chorrito de agua o aceite. 2. Agregar el brócoli al vapor y saltear por 5 minutos. 3. Incorporar la salsa de soya y servir junto al arroz integral.",
        recomendacionNutricional = "Combinación magra y de bajo índice glucémico que proporciona energía sostenida para el día."
    ),
    Minuta(
        dia = "Viernes",
        titulo = "Tortilla de espinacas y claras de huevo",
        ingredientes = "3 claras de huevo, 1 huevo entero, 1 taza de espinacas frescas picadas, 1/4 de cebolla picada, sal y pimienta al gusto",
        pasos = "1. Sofreír la cebolla y la espinaca en un sartén hasta que reduzcan. 2. Batir las claras con el huevo entero, sal y pimienta. 3. Verter los huevos sobre las verduras y cocinar a fuego lento por ambos lados.",
        recomendacionNutricional = "Opción rica en proteínas de alta calidad y baja en calorías, ideal para la reparación muscular."
    )
)

enum class Pantalla {
    LOGIN, REGISTRO, RECUPERAR, MINUTA
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriDiariaTheme {
                NutriDiarioApp()
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    irARegistro: () -> Unit,
    irARecuperar: () -> Unit,
    irAMinuta: () -> Unit
) {

    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
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
                onClick = irAMinuta,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ingresar")
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

@Composable
fun NutriDiarioApp(){
    var pantallaActual by remember { mutableStateOf(Pantalla.LOGIN) }
    when (pantallaActual){
        Pantalla.LOGIN -> PantallaInicio(
            irARegistro = {pantallaActual = Pantalla.REGISTRO},
            irARecuperar = {pantallaActual = Pantalla.RECUPERAR},
            irAMinuta = { pantallaActual = Pantalla.MINUTA }
        )
        Pantalla.REGISTRO -> PantallaRegistro(
            irALogin = {pantallaActual = Pantalla.LOGIN}
        )

        Pantalla.RECUPERAR -> PantallaRecuperar(
            irALogin = {pantallaActual = Pantalla.LOGIN}
        )

        Pantalla.MINUTA -> PantallaMinuta()
    }
}

@Composable
fun PantallaRegistro(irALogin: () -> Unit) {
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
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("formulario de registro")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = irALogin) {
                Text("Volver a inicio de sesión")
            }
        }
    }
}

@Composable
fun PantallaRecuperar(irALogin: () -> Unit) {
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
            verticalArrangement = Arrangement.Center
        ) {
            Text("formulario de recuperación")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = irALogin) {
                Text("Volver a inicio de sesión")
            }
        }
    }
}
@Composable
fun PantallaMinuta() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu minuta semanal") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(minutas) { receta ->
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
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = receta.recomendacionNutricional,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}



