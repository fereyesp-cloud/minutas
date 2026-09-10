package com.fereyesp.nutridiaria.data
import androidx.compose.runtime.mutableStateListOf

data class Usuarios(
    val nombre: String,
    val usuario: String,
    val contrasena: String
)

val usuarios = mutableStateListOf(
    Usuarios(nombre = "Fernando Hierro", usuario = "fernando", contrasena = "1234"),
    Usuarios(nombre = "Ricardo Rodrigues", usuario = "ricardo", contrasena = "1234"),
    Usuarios(nombre = "Gabriela Tapia", usuario = "gabriela", contrasena = "1234"),
    Usuarios(nombre = "Alejandra del Pezo", usuario = "alejandra", contrasena = "1234"),
    Usuarios(nombre = "Marta Riquelme", usuario = "marta", contrasena = "1234")
)