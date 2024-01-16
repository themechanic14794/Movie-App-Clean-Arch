package com.example.movieapp.presentation.stateholders

import com.example.movieapp.domain.model.details.MovieDetails


data class MovieDetailsStateHolder(
    val isLoading:Boolean = false,
    val data : MovieDetails? = null,
    val error:String = ""
)
