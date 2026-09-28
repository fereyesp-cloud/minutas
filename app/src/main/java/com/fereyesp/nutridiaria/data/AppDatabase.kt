package com.fereyesp.nutridiaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuarios::class, Minuta::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun minutaDao(): MinutaDao

    companion object {
        @Volatile
        private var INSTANCIA: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nutridiaria_db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}