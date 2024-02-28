package com.example.movieapp.data.repository

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.domain.model.list.Movie
import com.example.movieapp.domain.repository.GetMovieRepository
import com.example.movieapp.data.remote.mappers.toDomain
import javax.inject.Inject

class GetMoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) : GetMovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return apiService.getMovieList(BuildConfig.API_KEY).results!!.toDomain()
    }
}