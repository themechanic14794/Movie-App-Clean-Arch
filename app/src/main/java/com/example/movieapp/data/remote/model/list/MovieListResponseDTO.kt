package com.example.movieapp.data.remote.model.list

data class MovieListResponseDTO(
    val page: Int?,
    val results: List<MovieDTO>?,
    val totalPages: Int?,
    val totalResults: Int?
)