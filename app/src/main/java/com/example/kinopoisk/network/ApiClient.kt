package com.example.kinopoisk.network

import com.example.kinopoisk.network.response.GetFilterResponse
import com.example.kinopoisk.network.response.GetMovieByIdResponse
import com.example.kinopoisk.network.response.GetMoviePageResponse
import retrofit2.Response

class ApiClient(
    private val movieApiService: MovieApiService
    ) {

    suspend fun getMovieById(movieId: Int): SimpleResponse<GetMovieByIdResponse>{
        return safeApiCall { movieApiService.getMovieById(movieId) }
    }

    suspend fun getMoviePageByQuery(
        pageIndex: Int,
        movieName: String
    ): SimpleResponse<GetMoviePageResponse>{
        return safeApiCall { movieApiService.getMoviePageByQuery(pageIndex, movieName) }
    }

    suspend fun getMovieFilters(): SimpleResponse<GetFilterResponse>{
        return safeApiCall { movieApiService.getMovieFilters() }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend ()-> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }
        catch(e: Exception){
            SimpleResponse.failure(e)
        }
    }
}