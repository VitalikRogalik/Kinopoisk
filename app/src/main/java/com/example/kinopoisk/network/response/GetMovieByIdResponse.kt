package com.example.kinopoisk.network.response

data class GetMovieByIdResponse(
    val completed: Boolean?,
    val countries: List<Country>? = listOf(),
    val coverUrl: String? = "",
    val description: String? = "",
    val editorAnnotation: String? = "",
    val endYear: Int? = 0,
    val filmLength: Int? = 0,
    val genres: List<Genre>? = listOf(),
    val has3D: Boolean?,
    val hasImax: Boolean?,
    val imdbId: String?,
    val isTicketsAvailable: Boolean?,
    val kinopoiskId: Int?,
    val lastSync: String? = "",
    val logoUrl: String? = "",
    val nameEn: String? = "",
    val nameOriginal: String? = "",
    val nameRu: String? = "",
    val posterUrl: String? = "",
    val posterUrlPreview: String? = "",
    val productionStatus: String? = "",
    val ratingAgeLimits: String? = "",
    val ratingAwait: Double?,
    val ratingAwaitCount: Int?,
    val ratingFilmCritics: Double?,
    val ratingFilmCriticsVoteCount: Int?,
    val ratingGoodReview: Double?,
    val ratingGoodReviewVoteCount: Int?,
    val ratingImdb: Double?,
    val ratingImdbVoteCount: Int?,
    val ratingKinopoisk: Double?,
    val ratingKinopoiskVoteCount: Int?,
    val ratingMpaa: String? = "",
    val ratingRfCritics: Double?,
    val ratingRfCriticsVoteCount: Int?,
    val reviewsCount: Int?,
    val serial: Boolean?,
    val shortDescription: String? = "",
    val shortFilm: Boolean?,
    val slogan: String? = "",
    val startYear: Int?,
    val type: String? = "",
    val webUrl: String? = "",
    val year: Int?
) {
    data class Country(
        val country: String = ""
    )

    data class Genre(
        val genre: String = ""
    )
}