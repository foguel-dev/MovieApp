package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.remote.MovieDataSource

class MovieRepositoryImpl(private val datasource: MovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = datasource.getUpcomingMovies()

    override suspend fun getTopRateMovies(): MovieList = datasource.getTopRateMovies()

    override suspend fun getPopularMovies(): MovieList = datasource.getPopularMovies()
}