package com.fereyesp.nutridiaria

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test
import com.fereyesp.nutridiaria.ui.screen.esCorreoValido

class EsCorreoValidoTest {
    @Test
    fun `correo valido retorna true`() {
        assertTrue("fernando@gmail.com".esCorreoValido())
    }

    @Test
    fun `correo sin arroba retorna false`() {
        assertFalse("fernandogmail.com".esCorreoValido())
    }

    @Test
    fun `correo sin dominio retorna false`() {
        assertFalse("fernando@".esCorreoValido())
    }

    @Test
    fun `correo vacio retorna false`() {
        assertFalse("".esCorreoValido())
    }
}