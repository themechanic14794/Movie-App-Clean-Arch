package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.list.Movie

interface GetMovieRepository {

    suspend fun getMovies() : List<Movie>
}