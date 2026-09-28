package com.fereyesp.nutridiaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minutas")
data class Minuta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dia: String,
    val titulo: String,
    val ingredientes: String,
    val pasos: String,
    val recomendacionNutricional: String
) {
    private fun normalizar(texto: String): String {
        return texto.trim().lowercase()
    }

    fun contieneIngredientes(busqueda: String): Boolean {
        return normalizar(ingredientes).contains(normalizar(busqueda))
    }

    val cantidadIngredientes: Int
        get() = ingredientes.split(",").size
}
