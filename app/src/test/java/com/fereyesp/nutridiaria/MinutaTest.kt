package com.fereyesp.nutridiaria

import com.fereyesp.nutridiaria.local.Minuta
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class MinutaTest {

    private val recetaDePrueba = Minuta(
        dia = "Lunes",
        titulo = "Avena con frutos rojos",
        ingredientes = "avena, chía, leche, frutos rojos, miel",
        pasos = "Mezclar todo",
        recomendacionNutricional = "Rica en fibra"
    )

    @Test
    fun `contieneIngredientes encuentra un ingrediente existente`() {
        assertTrue(recetaDePrueba.contieneIngredientes("avena"))
    }

    @Test
    fun `contieneIngredientes es insensible a mayusculas`() {
        assertTrue(recetaDePrueba.contieneIngredientes("AVENA"))
    }

    @Test
    fun `contieneIngredientes retorna false si no existe`() {
        assertFalse(recetaDePrueba.contieneIngredientes("pollo"))
    }

    @Test
    fun `cantidadIngredientes cuenta correctamente`() {
        assertEquals(5, recetaDePrueba.cantidadIngredientes)
    }
}