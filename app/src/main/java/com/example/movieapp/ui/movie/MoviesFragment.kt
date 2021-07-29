package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient
import kotlin.math.log


class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    //Instancia del viewmodel
    private val viewmodel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(
                    RetrofitClient.webservice
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        viewmodel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData","Upcoming: ${result.data.first} \n \n TopRate: ${result.data.second} \n \n Popular:${result.data.third}")
                }
                is Resource.Error -> {
                    Log.d("Error","${result.exception}")
                }

            }
        })

        //Forma incorrecta
/*        viewmodel.fetchPopularMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveDataPopular","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveDataPopular","${result.data}")
                }
                is Resource.Error -> {
                    Log.d("ErrorPopular","${result.exception}")
                }

            }
        })

        viewmodel.fetchTopRateMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveDataTopRate","Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveDataTopRate","${result.data}")
                }
                is Resource.Error -> {
                    Log.d("ErrorPopular","${result.exception}")
                }

            }
        })*/


    }

}