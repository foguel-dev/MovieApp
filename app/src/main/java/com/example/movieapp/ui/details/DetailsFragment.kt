package com.example.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {
    //Referencia a las vistas del layouts
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.ivPelicula)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}").centerCrop().into(binding.ivbackground)
        binding.tvDescription.text = args.overview
        binding.tvTitle.text = args.title
        binding.tvLanguage.text = "Language ${args.language}"
        binding.tvRating.text = "${args.voteAverage} (${args.voteCount} Reviews)"
        binding.tvCalendar.text = "Released ${args.releaseDate}"
    }

}