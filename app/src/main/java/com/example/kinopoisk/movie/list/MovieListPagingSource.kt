package com.example.kinopoisk.movie.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.mappers.MovieMapper
import com.example.kinopoisk.domain.models.Movie
import com.example.kinopoisk.network.NetworkLayer

class MovieListPagingSource(
    private val userSearch: String
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val pageNumber = params.key ?: 1

        val pageRequest = NetworkLayer.apiClient.getMoviePageByQuery(pageNumber, userSearch)

        pageRequest.exception?.let {
            return LoadResult.Error(it)
        }

        val movies = pageRequest.body.items.map{ response->
            MovieMapper.builtFrom(response)
        }
        val nextKey = if(pageNumber < pageRequest.body.totalPages) pageNumber + 1 else null
        val previousKey = if (pageNumber == 1) null else pageNumber-1

        return LoadResult.Page(movies, previousKey, nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}