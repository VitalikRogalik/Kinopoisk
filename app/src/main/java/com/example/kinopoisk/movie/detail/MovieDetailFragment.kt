package com.example.kinopoisk.movie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.transform.BlurTransformation
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMovieDetailBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by activityViewModels()
    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appBarSetup()

        //binding.shimmer.startShimmer()
        viewModel.fetchMovie(safeArgs.movieId)

        viewModel.movieByIdLiveData.observe(viewLifecycleOwner){ movie->

            /*binding.shimmer.visibility = View.GONE
            binding.shimmer.stopShimmer()
            binding.coordinatorLayout.visibility = View.VISIBLE
*/
            if(movie == null){
                Toast.makeText(activity, "Ошибка", Toast.LENGTH_LONG).show()
            }else{
                binding.apply {
                     movieBackgroundImage.load(movie.posterUrl) {
                        transformations(BlurTransformation(requireContext(), 25f))
                     }
                     movieImage.load(movie.posterUrl)

                     movieName.text = movie.nameRu ?: movie.nameOriginal
                     rating.text = movie.ratingKinopoisk?.toString() ?: ""
                     movieNameOriginal.text = movie.nameOriginal
                     movieYear.text = movie.year.toString()
                     movieGenre.text = movie.genres!![0].genre
                     movieCountry.text = movie.countries!![0].country
                     movieLength.text = movie.formattedMovieLength()
                     movieAgeLimit.text = movie.formattedAgeLimit()
                     movieDescription.text = movie.description

                    if (movie.ratingKinopoisk != null) {
                        when (movie.ratingKinopoisk) {
                            in 0.0..5.0 -> rating.setTextColor(resources.getColor(R.color.red))
                            in 5.0..7.0 -> rating.setTextColor(resources.getColor(R.color.grey))
                            else -> rating.setTextColor(resources.getColor(R.color.green))
                        }
                    }
                }
            }
        }

        binding.movieAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            if(abs(verticalOffset) == (appBarLayout.totalScrollRange)){
                binding.toolbarMovie.background = resources.getDrawable(R.color.white)
            }
            else{
                binding.toolbarMovie.background = null
            }
        })

        val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieDescriptionFragment()

        binding.movieDescription.setOnClickListener{
            navController.navigate(action)
        }
        binding.readMore.setOnClickListener{
            navController.navigate(action)
        }

    }

    private fun appBarSetup(){
        navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarMovie
            .setupWithNavController(navController, appBarConfiguration)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}