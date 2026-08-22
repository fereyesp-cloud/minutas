package com.fereyesp.nutridiaria.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fereyesp.nutridiaria.Pantalla


/*
* Valida las logica de las pantallas de la APP
* */
@Composable
fun NutriDiarioApp(){
    var pantallaActual by remember { mutableStateOf(Pantalla.LOGIN) }
    when (pantallaActual){
        Pantalla.LOGIN -> Login(
            irARegistro = {pantallaActual = Pantalla.REGISTRO},
            irARecuperar = {pantallaActual = Pantalla.RECUPERAR},
            irAMinuta = { pantallaActual = Pantalla.MINUTA },
            onIniciarSesion = { nombre -> pantallaActual = Pantalla.MINUTA }
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