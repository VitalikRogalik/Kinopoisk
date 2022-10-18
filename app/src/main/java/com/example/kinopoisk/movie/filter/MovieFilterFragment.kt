package com.example.kinopoisk.movie.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.databinding.FragmentMovieFilterBinding


class MovieFilterFragment: Fragment() {
    private val movieFilterViewModel: MovieFilterViewModel by activityViewModels()

    private lateinit var binding: FragmentMovieFilterBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        setNavigationComponent()

        movieFilterViewModel.getMovieFilters()

        binding.movieGenre.setOnClickListener {
            val direction = MovieFilterFragmentDirections
                .actionMovieSortFragmentToMovieFilterGenreSelectionFragment(true)
            navController.navigate(direction)
        }

        binding.movieCountry.setOnClickListener {
            val direction = MovieFilterFragmentDirections
                .actionMovieSortFragmentToMovieFilterGenreSelectionFragment(false)
            navController.navigate(direction)
        }
    }

    private fun setNavigationComponent(){
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }
}