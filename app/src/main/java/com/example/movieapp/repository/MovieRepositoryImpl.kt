package com.example.movieapp.repository

import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.remote.RemoteDataSource

class MovieRepositoryImpl(
    private val datasource: RemoteDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    //obtenemos las peliculas pero con extension function mapeamos, una movie a movieentity, y accedemos a upcoming
    override suspend fun getUpcomingMovies(): MovieList {
        datasource.getUpcomingMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }
        //filtrado de datasource local
        return dataSourceLocal.getUpcomingMovies()
    }

    //obtenemos las peliculas pero con extension function mapeamos, una movie a movieentity, y accedemos a toprated
    override suspend fun getPopularMovies(): MovieList {
        datasource.getPopularMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }
        //filtrado de datasource local
        return dataSourceLocal.getPopularMovies()
    }

    //obtenemos las peliculas pero con extension function mapeamos, una movie a movieentity, y accedemos a nowplaying
    /*override suspend fun getNowPlaying(): MovieList {
        datasource.getNowPlaying().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("nowplaying"))
        }
        //filtrado de datasource local
        return dataSourceLocal.getNowPlaying()
    }*/

    override suspend fun getTopRateMovies(): MovieList {
        datasource.getTopRatedMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("top_rated"))
        }
        //filtrado de datasource local
        return dataSourceLocal.getTopRatedMovies()
    }
}