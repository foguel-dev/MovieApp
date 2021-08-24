package com.example.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.MovieEntity

/**
 * Created by Yamil Vogl on 20/8/2021
 */
//Necesita persistir informacion, le pasamos el tipo de informacion guardaremos
@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase(){
    //Para acceder al insert de MovieDao
    abstract fun movieDao(): MovieDao

    //Crear una instancia room, como singleton, no tener mas de una instancia
    //Evitar si tenemos varias instancia de room, tener una unica bd, evita problemas para consultar informacion
    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "movie_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}