package com.example.kinopoisk.movie.filter

import com.example.kinopoisk.network.NetworkLayer
import com.example.kinopoisk.network.response.GetFilterResponse

class MovieFilterRepository {

    suspend fun getFilters(): GetFilterResponse?{
        val request = NetworkLayer.apiClient.getMovieFilters()

        if(request.failed || !request.isSuccessful){
            return null
        }

        return request.body
    }
}