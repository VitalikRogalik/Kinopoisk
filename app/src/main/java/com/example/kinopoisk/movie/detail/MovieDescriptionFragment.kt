package com.example.kinopoisk.movie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMovieDescriptionBinding

class MovieDescriptionFragment : Fragment() {
    private var _binding: FragmentMovieDescriptionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarMovieDescription)
            .setupWithNavController(navController, appBarConfiguration)

        viewModel.movieByIdLiveData.observe(viewLifecycleOwner){ movie->
            binding.movieDescription.text = movie?.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}