package com.example.movieapp.presentation.movies.stateholders

import com.example.movieapp.domain.model.list.Movie

data class MovieStateHolder (
    val isLoading:Boolean = false,
    val data : List<Movie>? = null,
    val error : String = ""
)