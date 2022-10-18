package com.example.kinopoisk.movie.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoisk.databinding.MovieListItemBinding
import com.example.kinopoisk.domain.models.Movie

class MovieListRvAdapter(private val onMovieSelected: (Int)-> Unit) : PagingDataAdapter<Movie, MovieListViewHolder>(
    UserComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onMovieSelected
        )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null, ViewHolder must support binding null item as placeholder
        if(item!=null){
            holder.bind(item)
        }
    }

    companion object {
        private val UserComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.kinopoiskId == newItem.kinopoiskId

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}