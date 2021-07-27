package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movieapp.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

    }

}