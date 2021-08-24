package com.example.movieapp.data.local

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

/**
 * Created by Yamil Vogl on 24/8/2021
 */
class LocalMovieDataSource (private val movieDao: MovieDao){
    //Accede a todas las peliculas pero filtra upcoming
    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }
    //Accede a todas las peliculas pero filtra top rated
    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }
    //Accede a todas las peliculas pero filtra popular
    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }
    //Accede a todas las peliculas pero filtra nowplaying
    suspend fun getNowPlaying(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "nowplaying" }.toMovieList()
    }
//Guarda pelucula
    suspend fun saveMovie(movie:MovieEntity){
        saveMovie(movie)
    }
}