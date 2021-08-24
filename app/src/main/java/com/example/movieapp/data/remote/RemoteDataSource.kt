package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService

//Instancia a webservices
class RemoteDataSource(private val webservice: WebService) {

    suspend fun getUpcomingMovies(): MovieList {
        return webservice.getUpcomingMovies(AppConstants.API_KEY)
    }

    suspend fun getTopRatedMovies(): MovieList {
        return webservice.getTopRateMovies(AppConstants.API_KEY)
    }

    suspend fun getPopularMovies(): MovieList {
        return webservice.getPopularMovies(AppConstants.API_KEY)
    }

    suspend fun getNowPlaying(): MovieList {
        return webservice.getNowPlaying(AppConstants.API_KEY)
    }
}