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
    private fun normalizar(texto: String): String {
        return texto.trim().lowercase()
    }

    fun contieneIngredientes(busqueda: String): Boolean {
        return normalizar(ingredientes).contains(normalizar(busqueda))
    }

    val cantidadIngredientes: Int
        get() = ingredientes.split(",").size
}