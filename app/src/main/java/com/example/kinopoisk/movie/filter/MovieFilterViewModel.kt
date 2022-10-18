package com.example.kinopoisk.movie.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.network.response.GetFilterResponse
import kotlinx.coroutines.launch

class MovieFilterViewModel: ViewModel() {
    private val repository = MovieFilterRepository()

    private var _filtersLiveData = MutableLiveData<GetFilterResponse?>()
    val filterLiveData: LiveData<GetFilterResponse?> = _filtersLiveData

    fun getMovieFilters(){
        viewModelScope.launch {
            val filters = repository.getFilters()
            _filtersLiveData.postValue(filters)
        }
    }
}