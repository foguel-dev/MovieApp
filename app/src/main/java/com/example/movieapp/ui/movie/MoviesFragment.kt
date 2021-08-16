package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.MovieDataSource
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MoviewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient
import com.example.movieapp.ui.movie.adapter.MovieAdapter
import com.example.movieapp.ui.movie.adapter.concat.NowPlayingConcantAdapter
import com.example.movieapp.ui.movie.adapter.concat.PopularConcatAdapter
import com.example.movieapp.ui.movie.adapter.concat.TopRateConcatAdapter
import com.example.movieapp.ui.movie.adapter.concat.UpcomingConcatAdapter


class MoviesFragment : Fragment(R.layout.fragment_movies), MovieAdapter.OnMovieClickListener {

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModels<MovieViewModel> { MoviewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice))) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(it.data.t1.results,this@MoviesFragment)))
                        addAdapter(1, TopRateConcatAdapter(MovieAdapter(it.data.t2.results,this@MoviesFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(it.data.t3.results,this@MoviesFragment)))
                        addAdapter(2, NowPlayingConcantAdapter(MovieAdapter(it.data.t4.results,this@MoviesFragment)))


                    }
                    binding.myRecyclerView.adapter = concatAdapter
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("FetchError", "Error: $it.exception ")
                    Toast.makeText(requireContext(), "Error: ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie.poster_path,movie.backdrop_path,movie.vote_average.toFloat(),movie.vote_count,movie.overview,movie.title,movie.original_language,movie.release_date)
        findNavController().navigate(action)
    }
}