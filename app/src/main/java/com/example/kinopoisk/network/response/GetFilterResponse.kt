package com.example.kinopoisk.network.response

data class GetFilterResponse(
    val genres: List<Genre>,
    val counties: List<Country>
) {
    data class Genre(
        val id: Int,
        val genre: String
    )

    data class Country(
        val id: Int,
        val country: String
    )
}