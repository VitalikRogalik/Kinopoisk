package com.example.kinopoisk.movie.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.MovieApplication.Companion.context
import com.example.kinopoisk.databinding.LoadingErrorBinding

class MovieListErrorAdapter(
    private val tryAgain: ()-> Unit
): LoadStateAdapter<MovieListErrorAdapter.Holder>(){

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val binding = LoadingErrorBinding.inflate(LayoutInflater.from(context))
        return Holder(binding, tryAgain)
    }

    class Holder(
        private val binding: LoadingErrorBinding,
        private val tryAgain: () -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        init {
            binding.tryAgainButton.setOnClickListener{tryAgain()}
        }

        fun bind(loadState: LoadState) = with(binding){

            tryAgainButton.isVisible = loadState is LoadState.Error
            tvMissingConnection.isVisible  = loadState is LoadState.Error
            progressBar.isVisible = loadState !is LoadState.Error
        }

    }
}