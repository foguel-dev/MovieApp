package com.example.movieapp.data.remote

import com.example.movieapp.application.AppConstants
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService

//Instancia a webservices
class MovieDataSource(private val webservice: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webservice.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRateMovies(): MovieList = webservice.getTopRateMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webservice.getPopularMovies(AppConstants.API_KEY)


}