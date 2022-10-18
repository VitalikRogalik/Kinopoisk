package com.example.kinopoisk.domain.models

data class Movie(
    val completed: Boolean?,
    val countries: List<Country>? = listOf(),
    val coverUrl: String? = "",
    val description: String? = "",
    val editorAnnotation: String? = "",
    val endYear: Int? = 0,
    val filmLength: Int? = 0,
    val genres: List<Genre>? = listOf(),
    val kinopoiskId: Int?,
    val logoUrl: String? = "",
    val nameRu: String? = "",
    val nameOriginal: String? = "",
    val posterUrl: String? = "",
    val posterUrlPreview: String? = "",
    val productionStatus: String? = "",
    val ratingAgeLimits: String? = "",
    val ratingImdb: Double?,
    val ratingImdbVoteCount: Int?,
    val ratingKinopoisk: Double?,
    val ratingKinopoiskVoteCount: Int?,
    val serial: Boolean?,
    val shortDescription: String? = "",
    val shortFilm: Boolean?,
    val slogan: String? = "",
    val startYear: Int?,
    val type: String? = "",
    val year: Int?
) {
    data class Country(
        val country: String = ""
    )

    data class Genre(
        val genre: String = ""
    )

    fun formattedMovieLength(): String?{
        if (filmLength!=null){
            val hours = filmLength.div(60)
            val minutes = filmLength.rem(60)
            return "$hours ч $minutes мин"
        }
        return null
    }

    fun formattedAgeLimit(): String?{
        if (ratingAgeLimits!=null){
            return "${ratingAgeLimits.substring(3)}+"
        }
        return null
    }
}