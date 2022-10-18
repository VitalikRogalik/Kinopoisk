package com.example.kinopoisk.movie.list.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.MovieListItemBinding
import com.example.kinopoisk.domain.models.Movie

class MovieListViewHolder(
    private val binding: MovieListItemBinding,
    private val onMovieSelected: (Int)-> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            movieRating.text = movie.ratingKinopoisk?.toString()?: ""
            if (movie.ratingKinopoisk != null){
                when(movie.ratingKinopoisk) {
                    in 0.0..5.0 -> movieRating.background = movieRating.context.resources.getDrawable(
                        R.color.red
                    )
                    in 5.0..7.0 -> movieRating.background = movieRating.context.resources.getDrawable(
                        R.color.grey
                    )
                    else -> movieRating.background = movieRating.context.resources.getDrawable(
                        R.color.green
                    )
                }
            }
            else
                movieRating.visibility = View.INVISIBLE

            imageMovie.load(movie.posterUrlPreview){
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            movieName.text = movie.nameRu ?: movie.nameOriginal
            movieGenre.text = movie.genres?.get(0)?.genre

            root.setOnClickListener{
                onMovieSelected(movie.kinopoiskId!!)
            }
        }
    }
}