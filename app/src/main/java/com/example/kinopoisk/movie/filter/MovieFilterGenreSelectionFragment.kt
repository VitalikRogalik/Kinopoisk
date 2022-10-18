package com.example.kinopoisk.movie.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMovieFilterGenreSelectionBinding

class MovieFilterGenreSelectionFragment: Fragment() {
    private val movieFilterViewModel: MovieFilterViewModel by activityViewModels()

    private lateinit var binding: FragmentMovieFilterGenreSelectionBinding
    private lateinit var navController: NavController
    private val safeArgs: MovieFilterGenreSelectionFragmentArgs by navArgs()
    private lateinit var movieFilterGenreRvAdapter: MovieFilterGenreRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieFilterGenreSelectionBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setNavigationComponent()


        if(safeArgs.booleanGenreCountry) {
            binding.toolbar.title = getString(R.string.genres)
            binding.headerTextView.text =
                String.format(getString(R.string.choose), getString(R.string.genre))
            //binding.filtersRecyclerView.adapter = movieFilterGenreRvAdapter(emptyList<Any>())

        }else {
            binding.toolbar.title = getString(R.string.countries)
            binding.headerTextView.text =
                String.format(getString(R.string.choose), getString(R.string.country))

        }
    }

    private fun setNavigationComponent(){
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }
}