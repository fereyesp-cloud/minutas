package com.fereyesp.nutridiaria.data

/**
 * Busqueda de receta
 */

data class Minuta(
    val dia: String,
    val titulo: String,
    val ingredientes: String,
    val pasos: String,
    val recomendacionNutricional: String
) {
    fun contieneIngredientes(busqueda: String): Boolean  {
        return ingredientes.contains(busqueda,ignoreCase = true)
    }
}