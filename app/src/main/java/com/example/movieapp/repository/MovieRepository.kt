package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

interface MovieRepository {
   suspend fun getUpcomingMovies(): MovieList
   suspend fun getTopRateMovies(): MovieList
   suspend fun getPopularMovies(): MovieList
}