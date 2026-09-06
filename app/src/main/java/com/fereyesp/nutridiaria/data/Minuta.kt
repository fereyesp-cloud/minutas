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

    val  cantidadIngredientes: Int
        get() = ingredientes.split(",").size
    fun contieneIngredientes(busqueda: String): Boolean  {
        return ingredientes.contains(busqueda,ignoreCase = true)
    }
}