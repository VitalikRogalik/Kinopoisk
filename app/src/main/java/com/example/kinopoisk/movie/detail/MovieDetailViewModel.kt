package com.example.kinopoisk.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.domain.models.Movie
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    private val repository = MovieRepository()

    private var _movieByIdLiveData = MutableLiveData<Movie?>()
    val movieByIdLiveData: LiveData<Movie?> = _movieByIdLiveData

    fun fetchMovie(movieId: Int){
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            _movieByIdLiveData.postValue(movie)
        }
    }

}