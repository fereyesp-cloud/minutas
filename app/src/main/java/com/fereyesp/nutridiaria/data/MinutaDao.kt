package com.fereyesp.nutridiaria.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MinutaDao {

    @Insert
    suspend fun insertar(minuta: Minuta)

    @Insert
    suspend fun insertarTodas(minutas: List<Minuta>)

    @Query("SELECT * FROM minutas")
    suspend fun obtenerTodas(): List<Minuta>

    @Query("SELECT * FROM minutas WHERE dia = :dia")
    suspend fun obtenerPorDia(dia: String): List<Minuta>

    @Query("SELECT * FROM minutas WHERE titulo = :titulo LIMIT 1")
    suspend fun buscarPorTitulo(titulo: String): Minuta?
}