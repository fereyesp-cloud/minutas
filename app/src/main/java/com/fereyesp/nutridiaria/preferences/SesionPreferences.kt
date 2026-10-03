package com.fereyesp.nutridiaria.preferences

import android.content.Context

class SesionPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("sesion_nutridiaria", Context.MODE_PRIVATE)

    fun guardarSesion(nombre: String, esAdmin: Boolean){
        prefs.edit()
            .putString("nombre_usuario", nombre)
            .putBoolean("es_admin", esAdmin)
            .apply()
    }

    fun obtenerNombreUsuario(): String? {
        return prefs.getString("nombre_usuario", null)
    }

    fun esAdmin(): Boolean {
        return prefs.getBoolean("es_admin", false)
    }

    fun cerrarSesion() {
        prefs.edit().clear().apply()
    }
}