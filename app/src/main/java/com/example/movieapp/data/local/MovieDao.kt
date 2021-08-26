package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.MovieEntity

/**
 * Created by Yamil Vogl on 20/8/2021
 */

@Dao
interface MovieDao {

    //Seleccionamos toda la lista de peliculas de nuestro modelo
    @Query("SELECT * FROM movieEntity")
    suspend fun getAllMovies():List<MovieEntity>

    //Guardamos todas las peliculas y evitamos conflicto de duplicaciones
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)
}