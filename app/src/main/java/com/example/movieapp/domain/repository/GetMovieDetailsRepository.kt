package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.details.MovieDetails

interface GetMovieDetailsRepository {

    suspend fun getMovieDetails(id:String) : MovieDetails

}