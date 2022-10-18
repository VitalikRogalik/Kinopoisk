package com.example.kinopoisk.movie.detail

import com.example.kinopoisk.domain.mappers.MovieMapper
import com.example.kinopoisk.domain.models.Movie
import com.example.kinopoisk.network.NetworkLayer

class MovieRepository {

    suspend fun getMovieById(movieId: Int): Movie?{
        val request = NetworkLayer.apiClient.getMovieById(movieId)

        if(request.failed || !request.isSuccessful){
            return null
        }

        val movie = MovieMapper.builtFrom(request.body)

        return movie
    }

}