package com.example.kinopoisk.domain.mappers

import com.example.kinopoisk.domain.models.Movie
import com.example.kinopoisk.network.response.GetMovieByIdResponse

object MovieMapper {

    fun builtFrom(response: GetMovieByIdResponse): Movie {
        val countries = arrayListOf<Movie.Country>()
        val genres = arrayListOf<Movie.Genre>()

        response.countries?.let{listOfCountry->
            listOfCountry.forEach{
                countries.add(Movie.Country(it.country))
            }
        }

        response.genres?.let{listOfGenres->
            listOfGenres.forEach{
                genres.add(Movie.Genre(it.genre))
            }
        }

        return Movie(
            completed = response.completed,
            countries = countries,
            coverUrl = response.coverUrl,
            description = response.description,
            editorAnnotation = response.editorAnnotation,
            endYear = response.endYear,
            filmLength = response.filmLength,
            genres = genres,
            kinopoiskId = response.kinopoiskId,
            logoUrl = response.logoUrl,
            nameRu = response.nameRu,
            nameOriginal = response.nameOriginal,
            posterUrl = response.posterUrl,
            posterUrlPreview = response.posterUrlPreview,
            productionStatus = response.productionStatus,
            ratingAgeLimits = response.ratingAgeLimits,
            ratingImdb = response.ratingImdb,
            ratingImdbVoteCount = response.ratingImdbVoteCount,
            ratingKinopoisk = response.ratingKinopoisk,
            ratingKinopoiskVoteCount = response.ratingKinopoiskVoteCount,
            serial = response.serial,
            shortDescription = response.shortDescription,
            shortFilm = response.shortFilm,
            slogan = response.slogan,
            startYear = response.startYear,
            type = response.type,
            year = response.year
        )
    }

}