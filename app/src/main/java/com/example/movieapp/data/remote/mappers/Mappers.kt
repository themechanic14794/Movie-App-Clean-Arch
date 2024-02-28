package com.example.movieapp.data.remote.mappers

import com.example.movieapp.data.remote.model.details.MovieDetailsDTO
import com.example.movieapp.data.remote.model.list.MovieDTO
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.list.Movie

fun List<MovieDTO>.toDomain() : List<Movie>{
    return map{
        Movie(
            id = it.id?:0,
            posterPath = it.poster_path?:"",
            title = it.title?:""
        )
    }
}

fun MovieDetailsDTO.toDomain() = MovieDetails(
    originalTitle = originalTitle?:"",
    overview = overview?:"",
    posterPath = poster_path?:"",
    tagline = tagline?:"",
    title = title?:""
)

