package com.example.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {
    //Referencia a las vistas del layouys
    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
    }

}