package com.example.kinopoisk.network

import com.example.kinopoisk.network.response.GetFilterResponse
import com.example.kinopoisk.network.response.GetMovieByIdResponse
import com.example.kinopoisk.network.response.GetMoviePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @Headers(
        "X-API-KEY: 20a5a27f-5018-428d-84bc-0f32740bc65e",
        "accept: application/json")
    @GET("films/{film-id}")
    suspend fun getMovieById(
        @Path("film-id") filmId: Int
    ): Response<GetMovieByIdResponse>

    @Headers(
        "X-API-KEY: 20a5a27f-5018-428d-84bc-0f32740bc65e",
        "accept: application/json")
    @GET("films")
    suspend fun getMoviePageWithQuery(
        @Query("page") pageIndex: Int,
        @Query("type") type: String,
        @Query("genres") genre: Int,
        @Query("countries") country: Int,
        @Query("yearFrom") yearFrom: Int,
        @Query("yearTo") yearTo: Int,
        @Query("ratingFrom") ratingFrom: Int,
        @Query("ratingTo") ratingTo: Int,
        @Query("order") order: String,
    ): Response<GetMoviePageResponse>

    @Headers(
        "X-API-KEY: 20a5a27f-5018-428d-84bc-0f32740bc65e",
        "accept: application/json")
    @GET("films/filters")
    suspend fun getMovieFilters(): Response<GetFilterResponse>

    @Headers(
        "X-API-KEY: 20a5a27f-5018-428d-84bc-0f32740bc65e",
        "accept: application/json")
    @GET("films")
    suspend fun getMoviePageByQuery(
        @Query("page") pageIndex: Int,
        @Query("keyword") movieName: String
    ): Response<GetMoviePageResponse>
}