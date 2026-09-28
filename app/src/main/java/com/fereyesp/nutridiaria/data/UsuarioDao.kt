package com.fereyesp.nutridiaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuarios)

    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuarios>

    @Query("SELECT * FROM usuarios WHERE usuario = :nombreUsuario AND contrasena = :clave LIMIT 1")
    suspend fun buscarPorCredenciales(nombreUsuario: String, clave: String): Usuarios?

    @Query("SELECT * FROM usuarios WHERE usuario = :nombreUsuario LIMIT 1")
    suspend fun buscarPorUsuario(nombreUsuario: String): Usuarios?
}