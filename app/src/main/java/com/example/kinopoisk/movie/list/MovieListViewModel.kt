package com.example.kinopoisk.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.kinopoisk.Constants

class MovieListViewModel: ViewModel() {
    private var currentUserSearch: String = ""
    private var pagingSource: MovieListPagingSource? = null
        get() {
            if (field == null || field?.invalid == true){
                field = MovieListPagingSource(currentUserSearch)
            }
            return field
        }

    val flow = Pager(
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            initialLoadSize = Constants.INITIAL_LOAD_SIZE,
            enablePlaceholders = false
            )
    ){
        pagingSource!!
    }.flow
        .cachedIn(viewModelScope)

    fun submitQuery(userSearch: String){
        currentUserSearch = userSearch
        pagingSource?.invalidate()          // указываем что pagingSource больше не действителен
                                            // а в гетере если он не действителен создается новый экземпляр
    }
}