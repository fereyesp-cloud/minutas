package com.fereyesp.nutridiaria

import com.fereyesp.nutridiaria.ui.screen.validarCampo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ValidarCampoTest {
    @Test
    fun `retorna null cuando el campo es valido`() {
        val resultado = validarCampo("Fernando", { it.isNotBlank() }, "El nombre es obligatorio")
        assertNull(resultado)
    }

    @Test
    fun `retorna el mensaje de error cuando el campo es invalido`() {
        val resultado = validarCampo("", { it.isNotBlank() }, "El nombre es obligatorio")
        assertEquals("El nombre es obligatorio", resultado)
    }

    @Test
    fun `valida longitud minima de contrasena`() {
        val resultado = validarCampo("123", { it.length >= 4 }, "La contraseña debe tener al menos 4 caracteres")
        assertEquals("La contraseña debe tener al menos 4 caracteres", resultado)
    }

    @Test
    fun `contrasena valida no retorna error`() {
        val resultado = validarCampo("1234", { it.length >= 4 }, "La contraseña debe tener al menos 4 caracteres")
        assertNull(resultado)
    }
}