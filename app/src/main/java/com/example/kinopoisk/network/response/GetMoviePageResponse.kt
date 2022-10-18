package com.example.kinopoisk.network.response

data class GetMoviePageResponse (
        val total: Int = 0,
        val totalPages: Int = 0,
        val items: List<GetMovieByIdResponse> = emptyList()
        )